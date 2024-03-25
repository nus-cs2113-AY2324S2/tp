package health;

// import utility.Parser;
// import utility.CustomExceptions;
// import utility.ErrorConstant;
// import utility.UiConstant;
// import utility.HealthConstant;

import java.time.LocalDate;

public class Appointment extends Health {
    protected String doctorNotes;
    /**
     * The Appointment category.
     */
    protected String appointmentCategory;

    protected LocalDate date;

    /**
     * Constructor for Appointment object.
     *
     * @param appointmentCategory A string representing the patient's specialist...genre? the word for it idk.
     * @param doctorNotes A string representing the doctor's notes to patient.
     * @throws AssertionError If height or weight values are not positive.
     */
    public Appointment(String appointmentCategory, String doctorNotes, String date) {

        // this.height = Double.parseDouble(height);
        // this.weight = Double.parseDouble(weight);

        // assert this.height > 0 && this.weight > 0 : HealthConstant.HEIGHT_WEIGHT_REQUIRE_POSITIVE;

        // this.date = Parser.parseDate(date);

        // this.bmiValue = calculateBmiValue();
        // this.bmiCategory = getBmiCategory(bmiValue);
    }

}
