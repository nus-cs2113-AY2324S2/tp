package meditracker.command;

import meditracker.medication.MedicationManager;
import meditracker.ui.Ui;
import meditracker.library.LibraryManager;

import java.io.IOException;

/**
 * Represents a command to search for medications from the local medication library based on a keyword.
 */
public class SearchCommand extends Command {

    private final String keyword;

    /**
     * Constructs a SearchCommand object with the specified keyword.
     *
     * @param arguments The keyword to search for.
     */
    public SearchCommand(String arguments) {
        this.keyword = arguments;
    }

    /**
     * Executes the search command to search for medications based on the provided keyword
     * and displays the search results on the user interface.
     *
     * @param medicationManager The MedicationManager object to manage medications.
     */
    @Override
    public void execute(MedicationManager medicationManager) {
        try {
            LibraryManager libraryManager = new LibraryManager();
            libraryManager.searchMedication(keyword);
            libraryManager.printSearchResults();
        } catch (IOException e) {
            Ui.showLibraryNotFoundMessage();
        }
    }
}
