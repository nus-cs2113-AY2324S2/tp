package customexceptions;

public class IncompletePromptException extends Exception {
    public static final String[] INSTRUCTIONS = {"add-inflow", "add-outflow", "delete-inflow", "delete-outflow",
            "login", "quit", "view-history"};
    private boolean isTypo = false;
    public IncompletePromptException(String line) {
        int spaceIndex = line.indexOf(" ");
        String firstWord = (spaceIndex == -1) ? line : line.substring(0, spaceIndex);
        checkTypo(firstWord);
    }
    public boolean getIsTypo() {
        return this.isTypo;
    }
    public void checkTypo(String word) {
        for (String instruction : INSTRUCTIONS) {
            if (!instruction.equals(word) && instruction.contains(word)) {
                if (!isTypo) {
                    isTypo = true;
                    System.out.println("Did you mean: ");
                }
                System.out.print(instruction + " ");
            }
        }
        if (isTypo) {
            System.out.println("?");
        }
    }
}
