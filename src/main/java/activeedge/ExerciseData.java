
package activeedge;

/**
 * The ExerciseData class stores information about various exercise activities.
 */
public class ExerciseData {
    /**
     * An array containing data for each exercise activity.
     * Each element is an array with two elements: exercise name and calories burnt per minute (as integers).
     */
    public static String[][] exercisesList = {
            {"running", "10"},
            {"cycling", "8"},
            {"swimming", "8"},
            {"jumping jacks", "10"},
            {"walking", "4"},
            {"yoga", "3"},
            {"pilates", "6"},
            {"weightlifting", "5"},
            {"aerobics", "10"},
            {"dancing", "9"},
            {"kickboxing", "8"},
            {"zumba", "9"},
            {"HIIT", "11"},
            {"rowing", "10"},
            {"skiing", "8"},
            {"snowboarding", "7"},
            {"hiking", "6"},
            {"climbing", "8"},
            {"jogging", "10"},
            {"basketball", "9"},
            {"tennis", "7"},
            {"badminton", "6"},
            {"squash", "8"},
            {"golf", "3"},
            {"table tennis", "3"},
            {"volleyball", "4"},
            {"football", "10"},
            {"rugby", "8"},
            {"cricket", "6"},
            {"baseball", "4"},
            {"softball", "6"},
            {"surfing", "4"},
            {"skateboarding", "5"},
            {"rock climbing", "10"},
            {"bouldering", "8"},
            {"circuit training", "9"},
            {"paddleboarding", "6"},
            {"kayaking", "6"},
            {"canoeing", "5"},
            {"martial arts", "9"},
            {"boxing", "8"},
            {"karate", "9"},
            {"taekwondo", "9"},
            {"judo", "8"},
            {"wrestling", "10"},
            {"muay thai", "10"},
            {"aikido", "8"},
            {"jiu-jitsu", "10"},
            {"piloxing", "9"},
            {"barre", "7"},
            {"spinning", "7"},
            {"ballet", "4"},
            {"salsa dancing", "8"},
            {"ballroom dancing", "5"},
            {"tango", "6"},
            {"flamenco", "7"},
            {"belly dancing", "7"},
            {"capoeira", "8"},
            {"pole dancing", "8"},
            {"circus arts", "7"},
            {"parkour", "10"},
            {"freerunning", "10"},
            {"tricking", "9"},
            {"slacklining", "5"},
            {"bungee jumping", "13"},
            {"skydiving", "10"},
            {"paragliding", "7"},
            {"kitesurfing", "7"},
            {"wakeboarding", "8"},
            {"snowmobiling", "6"},
            {"sledding", "8"},
            {"ice skating", "4"},
            {"roller skating", "5"},
            {"skateboarding", "5"},
            {"biking", "4"}
    };

    /**
     * Prints the exercise details for each exercise activity in the exerciseList array.
     */
    public static void printExercises() {
        for (int i = 0; i < exercisesList.length; i++) {
            System.out.println("Exercise Name: " + exercisesList[i][0]);
            System.out.println("Calories Burnt Per Minute: " + exercisesList[i][1]);
            System.out.println(); // Empty line for readability
        }
    }
    /**
     * Asserts that the exercise data is valid.
     * For example, it checks that each exercise has a non-empty name and a positive calories burnt value.
     */
    public static void assertExerciseData() {
        for (String[] exercise : exercisesList) {
            assert !exercise[0].isEmpty() : "Exercise name cannot be empty";
            int caloriesBurnt = Integer.parseInt(exercise[1]);
            assert caloriesBurnt > 0 : "Calories burnt per minute must be positive";
        }
    }

    public static void main(String[] args) {
        // Check exercise data validity
        assertExerciseData();

        // Print exercises
        printExercises();
    }
}
