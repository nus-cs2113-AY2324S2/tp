package longah.handler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Handles the creation, loading, authentication and resetting of the PIN.
 */
public class PINHandler {
    private static Logger logger = Logger.getLogger("PIN Logger");
    private static final String PIN_FILE_PATH = "./data/pin.txt";
    private Scanner scanner;

    /**
     * Constructs a new PINHandler instance.
     */
    public PINHandler() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns the file path of the PIN file where the PIN is encrypted and saved.
     *
     * @return The file path of the PIN file where the PIN is encrypted and saved.
     */
    public static java.lang.String getPinFilePath() {
        return PIN_FILE_PATH;
    }

    /**
     * Creates a new PIN for the user.
     */
    public void createPin() {
        System.out.print("Thanks for choosing LongAh! Never worry about owing money during the Year of the Dragon!\n" +
                "Create your 6-digit PIN: \n");
        String pin = scanner.nextLine();

        while (pin.length() != 6 || !pin.matches("\\d{6}")) {
            if (Objects.equals(pin, "quit")) {
                System.exit(0);
            }
            System.out.println("Invalid PIN. Your PIN must be a 6-digit number. " +
            "Please try again, or enter 'quit' to exit.");
            System.out.print("Enter a 6-digit PIN: ");
            pin = scanner.nextLine();
        }

        assert pin != null : "PIN should not be null.";
        assert pin.length() == 6 : "PIN should be 6 digits long.";

        try {
            // Encrypt PIN before saving
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedPin = md.digest(pin.getBytes(StandardCharsets.UTF_8));
            String hashedPinHex = new BigInteger(1, hashedPin).toString(16);
            Files.write(Paths.get(PIN_FILE_PATH), hashedPinHex.getBytes());
            System.out.println("PIN saved successfully!");
            logger.log(Level.INFO, "PIN saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving PIN. Please try again.");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads the saved PIN from the file.
     *
     * @return The saved PIN.
     */
    public String loadPin() {
        String savedPin = "";
        try {
            // Load hashed PIN after loading
            savedPin = new String(Files.readAllBytes(Paths.get(PIN_FILE_PATH)));
            logger.log(Level.INFO, "User loaded successfully!");
        } catch (IOException e) {
            System.out.println("Error reading saved PIN. Please try again.");
        }
        return savedPin;
    }

    /**
     * Authenticates the user by comparing the entered PIN with the saved PIN.
     */
    public void authenticate() {
        String savedPin = loadPin();
        assert savedPin != null : "Saved PIN should not be null.";

        System.out.print("Enter your PIN: ");
        String enteredPin = scanner.nextLine();
        assert enteredPin != null : "Entered PIN should not be null.";

        try {
            // Hash the entered PIN before comparing
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedEnteredPin = md.digest(enteredPin.getBytes(StandardCharsets.UTF_8));
            String hashedEnteredPinHex = new BigInteger(1, hashedEnteredPin).toString(16);

            while (!hashedEnteredPinHex.equals(savedPin)) {
                if (Objects.equals(enteredPin, "quit")) {
                    System.exit(0);
                }
                System.out.println("Invalid PIN. Please try again. Alternatively, enter 'quit' to exit.");
                System.out.print("Enter your PIN: ");
                enteredPin = scanner.nextLine();
                hashedEnteredPin = md.digest(enteredPin.getBytes(StandardCharsets.UTF_8));
                hashedEnteredPinHex = new BigInteger(1, hashedEnteredPin).toString(16);
            }
            logger.log(Level.INFO, "Login successful!");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error authenticating PIN. Please try again.");
        }
    }

    /**
     * Resets the PIN for the user.
     */
    public void resetPin() {
        System.out.print("Enter your current PIN: ");
        String enteredPin = scanner.nextLine();
        assert enteredPin != null : "Entered PIN should not be null.";

        try {
            // Hash the entered PIN before comparing
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedEnteredPin = md.digest(enteredPin.getBytes(StandardCharsets.UTF_8));
            String hashedEnteredPinHex = new BigInteger(1, hashedEnteredPin).toString(16);

            String savedPin = loadPin();
            if (hashedEnteredPinHex.equals(savedPin)) {
                // If the entered PIN is correct, allow the user to create a new PIN
                createPin();
            } else {
                System.out.println("Invalid PIN. Please try again.");
            }
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error resetting PIN. Please try again.");
        }
    }
}
