package classify.classgroup;

import java.util.ArrayList;

public class ClassList {

    //@@author Cryolian
    public ArrayList<Class> classList;

    public ClassList() {
        this.classList = new ArrayList<>();
    }

    public void addClass(Class classToAdd) {
        classList.add(classToAdd);
    }

    public void removeClass(int id) {
        classList.remove(id);
    }

    public void removeClass(Class classToRemove) {
        classList.remove(classToRemove);
    }
    
}
