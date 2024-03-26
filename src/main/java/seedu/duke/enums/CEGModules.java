package seedu.duke.enums;

public enum CEGModules {
    GESS(4),
    GEC(4),
    GEN(4),
    ES2631(4),
    CS1010(4),
    GEA1000(4),
    DTK1234(4),
    EG1311(4),
    IE2141(4),
    EE2211(4),
    EG2501(4),
    CDE2000(4),
    PF1101(4),
    CG4002(8),
    MA1511(2),
    MA1512(2),
    MA1508E(4),
    EG2401A(2),
    CP3880(12),
    EG3611A(10),
    CG1111A(4),
    CG2111A(4),
    CS1231(4),
    CG2023(4),
    CG2027(2),
    CG2028(2),
    CG2271(4),
    CS2040C(4),
    CS2113(4),
    EE2026(4),
    EE4204(4);

    private final int moduleMC;

    CEGModules(int moduleMC){
        this.moduleMC = moduleMC;
    }

    public int getModuleMC(){
        return moduleMC;
    }

    public static CEGModules mapStringToEnum(String moduleCode) {
        for (CEGModules cegModule : CEGModules.values()) {
            if (cegModule.name().equalsIgnoreCase(moduleCode)) {
                return cegModule;
            }
        }
        throw new IllegalArgumentException("No module code " + moduleCode + " found in CEGModules");
    }
}
