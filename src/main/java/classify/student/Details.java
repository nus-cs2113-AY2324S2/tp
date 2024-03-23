package classify.student;

import java.time.LocalDate;

/**
 * Important details of a student the tuition centre
 * has to keep track of.
 */
public abstract class Details {

    //@@author Cryolian
    protected String gender;
    protected int phoneNumber = 0;
    protected LocalDate lastPaymentDate = LocalDate.now();
    protected String remarks = "NA";

    public Details() {

    }
     
    public String getGender() {
        return gender;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getLastPaymentDate() {
        return lastPaymentDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setLastPaymentDate(LocalDate lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
