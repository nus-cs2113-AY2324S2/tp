package byteceps.commands;

public class InputArguments {
    private final String flag;
    private final String parameter;

    public InputArguments(String flag, String parameter) {
        this.flag = flag.trim();
        this.parameter = parameter.trim();
    }


    public String getFlag() {
        return flag;
    }

    public String getParameter() {
        return parameter;
    }

}
