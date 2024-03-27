package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.GradeCommand;

import java.util.Map;

public class GradeCommandMetadata extends CommandMetadata {
    private static final String GRADE_KEYWORD = "grade";
    private static final String GRADE_PATTERN_REGEX =
            "grade\\s+c/(?<courseCode>[A-Za-z]{2,3}\\d{4}[A-Za-z]?)\\s+g/(?<grade>[ab][+-]?|[cd][+]?|f|cs|cu)";
    private static final String[] GRADE_ARGUMENTS = {"courseCode", "grade"};

    public GradeCommandMetadata() {
        super(GRADE_KEYWORD, GRADE_PATTERN_REGEX, GRADE_ARGUMENTS);
    }

    // Grade Command Creator
    @Override
    protected Command createCommandInstance(Map<String, String> args) {
        String moduleCode = args.getOrDefault("courseCode", "COURSECODE_ERROR");
        String grade = args.getOrDefault("grade", "GRADE_ERROR");

        return new GradeCommand(moduleCode, grade);
    }
}
