package classify.student;

// import classify.classgroup.Class;
import java.util.ArrayList;

/**
 * Important details of a student the tuition centre
 * has to keep track of.
 */
public class Details {

    //@@author Cryolian
    public String gender = "unknown";
    public int phoneNumber = 0;
    // public Class currentClass = new Class(0, "Unknown");
    public ArrayList<String> subjects = new ArrayList<>();
    public String lastPaymentDate = "unknown";
    public String remarks = "NA";

    public Details() {
        
    }
     
    public String getGender() {
        return gender;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getLastPaymentDate() {
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

    public void setLastPaymentDate(String lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
