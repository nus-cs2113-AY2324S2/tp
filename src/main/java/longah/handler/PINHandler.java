package longah.handler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Handles the creation, loading, authentication, and resetting of the PIN.
 */
public class PINHandler {
    private static final Logger logger = Logger.getLogger("PIN Logger");
    private static final String PIN_FILE_PATH = "./data/pin.txt";
    private String savedPin;
    private boolean authenticationEnabled;

    // @@author jing-xiang
    /**
     * Constructs a new PINHandler instance.
     */
    public PINHandler() {
        StorageHandler.initDir();
        loadPinAndAuthenticationEnabled();
        if (!Files.exists(Paths.get(PINHandler.getPinFilePath())) || savedPin.isEmpty()) {
            createPin();
        }
        if (authenticationEnabled) {
            authenticate();
        }
    }

    /**
     * Loads the saved PIN and authentication enabled state from the file.
     */
    private void loadPinAndAuthenticationEnabled() {
        try {
            String[] data = new String(Files.readAllBytes(Paths.get(PIN_FILE_PATH))).split("\n");
            savedPin = data[0];
            if (data.length > 1) {
                authenticationEnabled = Boolean.parseBoolean(data[1].trim());
            }
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            UI.showMessage("Error reading saved PIN and authentication enabled state.");
        }
    }

    /**
     * Saves the PIN and authentication enabled state to the file.
     */
    private void savePinAndAuthenticationEnabled() {
        try {
            String data = savedPin + "\n" + authenticationEnabled;
            Files.write(Paths.get(PIN_FILE_PATH), data.getBytes());
        } catch (IOException e) {
            UI.showMessage("Error saving PIN and authentication enabled state.");
        }
    }

    /**
     * Returns the file path of the PIN file where the PIN is encrypted and saved.
     *
     * @return The file path of the PIN file where the PIN is encrypted and saved.
     */
    public static String getPinFilePath() {
        return PIN_FILE_PATH;
    }

    /**
     * Creates a new PIN for the user.
     */
    public void createPin() {
        UI.showMessage("Create your 6-digit PIN:");
        String pin = UI.getUserInput();

        // check if the input is a 6-digit number
        while (pin.length() != 6 || !pin.matches("\\d{6}")) {
            if (Objects.equals(pin, "exit")) {
                System.exit(0);
            }
            UI.showMessage("Invalid PIN. Your PIN must be a 6-digit number. " +
                    "Please try again, or enter 'exit' to exit LongAh.");
            UI.showMessage("Enter a 6-digit PIN: ", false);
            pin = UI.getUserInput();
        }

        assert pin != null : "PIN should not be null.";
        assert pin.length() == 6 : "PIN should be 6 digits long.";

        try {
            // Encrypt PIN before saving
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedPin = md.digest(pin.getBytes(StandardCharsets.UTF_8));
            String hashedPinHex = new BigInteger(1, hashedPin).toString(16);
            savedPin = hashedPinHex;
            savePinAndAuthenticationEnabled();
            UI.showMessage("PIN saved successfully!");
            logger.log(Level.INFO, "PIN saved successfully!");
        } catch (NoSuchAlgorithmException e) {
            UI.showMessage("Error saving PIN. Please try again.");
        }
    }

    /**
     * Authenticates the user by comparing the entered PIN with the saved PIN.
     */
    public void authenticate() {
        if (!authenticationEnabled) {
            return;
        }
        assert savedPin != null : "Saved PIN should not be null.";

        UI.showMessage("Enter your PIN: ", false);
        String enteredPin = UI.getUserInput();
        assert enteredPin != null : "Entered PIN should not be null.";

        try {
            // Hash the entered PIN before comparing
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedEnteredPin = md.digest(enteredPin.getBytes(StandardCharsets.UTF_8));
            String hashedEnteredPinHex = new BigInteger(1, hashedEnteredPin).toString(16);

            while (!hashedEnteredPinHex.equals(savedPin)) {
                if (Objects.equals(enteredPin, "exit")) {
                    System.exit(0);
                }
                UI.showMessage("Invalid PIN. Please try again. Alternatively, enter 'exit' to exit LongAh.");
                UI.showMessage("Enter your PIN:");
                enteredPin = UI.getUserInput();
                hashedEnteredPin = md.digest(enteredPin.getBytes(StandardCharsets.UTF_8));
                hashedEnteredPinHex = new BigInteger(1, hashedEnteredPin).toString(16);
            }
            logger.log(Level.INFO, "Login successful!");
        } catch (NoSuchAlgorithmException e) {
            UI.showMessage("Error authenticating PIN. Please try again.");
        }
    }

    /**
     * Resets the PIN for the user or enables/disables authentication upon startup.
     */
    public void resetPin() {
        UI.showMessage("Enter your current PIN: (or enter enable/disable to control startup authentication!)", false);
        String enteredPin = UI.getUserInput();
        assert enteredPin != null : "Entered PIN should not be null.";
        if (Objects.equals(enteredPin, "disable")) {
            authenticationEnabled = false;
            savePinAndAuthenticationEnabled();
            UI.showMessage("Authentication disabled upon startup.");
            return;
        } else if (Objects.equals(enteredPin, "enable")) {
            authenticationEnabled = true;
            savePinAndAuthenticationEnabled();
            UI.showMessage("Authentication enabled upon startup.");
            return;
        }

        try {
            // Hash the entered PIN before comparing
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedEnteredPin = md.digest(enteredPin.getBytes(StandardCharsets.UTF_8));
            String hashedEnteredPinHex = new BigInteger(1, hashedEnteredPin).toString(16);

            if (hashedEnteredPinHex.equals(savedPin)) {
                // If the entered PIN is correct, allow the user to create a new PIN
                createPin();
            } else {
                UI.showMessage("Invalid PIN. Please try again.");
            }
        } catch (NoSuchAlgorithmException e) {
            UI.showMessage("Error resetting PIN. Please try again.");
        }
    }
}
