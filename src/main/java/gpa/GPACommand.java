package gpa;

import static seedu.duke.CantVasMain.ui;

public class GPACommand {

    public static void processGPACommand() {

        while (true) {
            System.out.println("Enter 'GPA' to start or 'exit' to exit to main menu:");
            String command = ui.getUserCommand();

            if ("exit".equalsIgnoreCase(command)) {
                System.out.println("Exiting the GPA calculator. Thank you for using it!");
                break;
            } else if (!"GPA".equalsIgnoreCase(command)) {
                System.out.println("Invalid input. " +
                        "Please input 'GPA' to start or 'exit' to exit.");
                continue;
            }

            System.out.println("Enter your current GPA and the number of MCs taken" +
                    " (format: GPA_SCORE /NUMBER_OF_MCS):");
            String gpaInput = ui.getUserCommand();
            if ("exit".equalsIgnoreCase(gpaInput.trim())) {
                System.out.println("Exiting the GPA calculator. Thank you for using it!");
                break;
            }

            double currentGPA = Double.parseDouble(gpaInput.split("/")[0].trim());
            int totalAccumulatedCredits = Integer.parseInt(gpaInput.split("/")[1].trim());

            // Input validation for negative numbers
            if (currentGPA < 0 || currentGPA > 5 || totalAccumulatedCredits < 0) {
                System.out.println("Invalid input: GPA score should be between 0 and 5," +
                        " and MCs should not be negative.");
                continue; // Skip the rest of the loop iteration and prompt again
            }

            // Assertions for additional validation
            assert currentGPA >= 0 && currentGPA <= 5 : "GPA should be between 0 and 5.";
            assert totalAccumulatedCredits >= 0 : "MCs should not be negative.";

            System.out.println("Enter the number of mods you want to add (format: NUMBER_OF_MODS):");
            String modsInput = ui.getUserCommand();
            if ("exit".equalsIgnoreCase(modsInput.trim())) {
                System.out.println("Exiting the GPA calculator. Thank you for using it!");
                break;
            }

            int numOfModules = Integer.parseInt(modsInput.trim());
            if (numOfModules < 0) {
                System.out.println("Invalid input: Number of modules cannot be negative.");
                continue;
            }

            // Assertion for the number of modules
            assert numOfModules >= 0 : "Number of modules should not be negative.";

            int[] moduleCredits = new int[numOfModules];
            String[] moduleGrades = new String[numOfModules];

            for (int i = 0; i < numOfModules; i++) {
                System.out.println("Enter modular credit and expected grade for module " + (i + 1)
                        + " (format: MODULAR_CREDIT /EXPECTED_GRADE):");
                String modInput = ui.getUserCommand();
                if ("exit".equalsIgnoreCase(modInput.trim())) {
                    System.out.println("Exiting the GPA calculator. Thank you for using it!");
                    return;
                }

                moduleCredits[i] = Integer.parseInt(modInput.split("/")[0].trim());
                moduleGrades[i] = modInput.split("/")[1].trim().toUpperCase();
            }

            double updatedGPA = GPAMain.calculateNewGPA(currentGPA,
                    totalAccumulatedCredits, numOfModules, moduleCredits, moduleGrades);
            System.out.printf("Your updated GPA is: %.2f\n", updatedGPA);
        }
    }
}
