package classify.student;

import classify.classgroup.Class;
import java.util.ArrayList;

public class Details {

    //@@author Cryolian
    public String gender;
    public int phoneNumber;
    public Class currentClass;
    public ArrayList<String> subjects;
    public String lastPaymentDate;
    public String remarks;
    
    public String getGender() {
        return gender;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public Class getCurrentClass() {
        return currentClass;
    }

    public ArrayList<String> getSubjects() {
        return subjects;
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

    public void setCurrentClass(Class currentClass) {
        this.currentClass = currentClass;
    }
}
