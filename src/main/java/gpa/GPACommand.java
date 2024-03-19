package gpa;

import static seedu.duke.CantVasMain.ui;

public class GPACommand {

    public static void processGPACommand() {

        while (true) { // Changed to an infinite loop to handle input until "exit" is encountered
            System.out.println("Enter 'GPA' to start or 'exit' to exit to main menu:");
            String command = ui.getUserCommand();

            // Check for exit command immediately
            if ("exit".equalsIgnoreCase(command)) {
                System.out.println("Exiting the GPA calculator. Thank you for using it!");
                break; // Exit the loop and method, ending the program or going back to the main menu
            } else if (!"GPA".equalsIgnoreCase(command)) {
                // Handle invalid input without changing the loop control variable
                System.out.println("Invalid input. Please input 'GPA' to start or 'exit' to exit.");
                continue; // Skip the rest of the loop iteration and prompt again
            }

            // If we reach this point, "GPA" was entered
            System.out.println("Enter your current GPA and the number of MCs taken" +
                    " (format: GPA_SCORE /NUMBER_OF_MCS):");
            String gpaInput = ui.getUserCommand();
            if ("exit".equalsIgnoreCase(gpaInput.trim())) {
                System.out.println("Exiting the GPA calculator. Thank you for using it!");
                break;
            }

            double currentGPA = Double.parseDouble(gpaInput.split("/")[0].trim());
            int totalAccumulatedCredits = Integer.parseInt(gpaInput.split("/")[1].trim());

            System.out.println("Enter the number of mods you want to add (format: NUMBER_OF_MODS):");
            String modsInput = ui.getUserCommand();
            if ("exit".equalsIgnoreCase(modsInput.trim())) {
                System.out.println("Exiting the GPA calculator. Thank you for using it!");
                break;
            }

            int numOfModules = Integer.parseInt(modsInput.trim());
            int[] moduleCredits = new int[numOfModules];
            String[] moduleGrades = new String[numOfModules];

            for (int i = 0; i < numOfModules; i++) {
                System.out.println("Enter modular credit and expected grade for module " + (i + 1)
                        + " (format: MODULAR_CREDIT /EXPECTED_GRADE):");
                String modInput = ui.getUserCommand();
                if ("exit".equalsIgnoreCase(modInput.trim())) {
                    System.out.println("Exiting the GPA calculator. Thank you for using it!");
                    return; // Exit the method entirely, ending the program or going back to the main menu
                }

                moduleCredits[i] = Integer.parseInt(modInput.split("/")[0].trim());
                moduleGrades[i] = modInput.split("/")[1].trim().toUpperCase();
            }

            double updatedGPA = GPAMain.calculateNewGPA(currentGPA,
                    totalAccumulatedCredits, numOfModules, moduleCredits, moduleGrades);
            System.out.printf("Your updated GPA is: %.2f\n", updatedGPA);
            // Loop will continue after calculation, allowing for new inputs or exit
        }
    }
}
