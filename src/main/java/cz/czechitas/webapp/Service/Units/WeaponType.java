package cz.czechitas.webapp.Service.Units;

public enum WeaponType {
    GREAT {public String toString() {return "Great weapon";}},
    HALBERD {public String toString() {return "Halberd";}},
    HANDWEAPON {public String toString() {return "Hand weapon";}},
    LANCE {public String toString() {return "Lance";}},
    LIGHT_LANCE {public String toString() {return "Light lance";}},
    PAIRED {public String toString() {return "Paired weapons";}},
    SPEAR {public String toString() {return "Spear";}},
    HW_SHIELD {public String toString() {return "Hand weapon and shield";}},
    SPEAR_SHIELD {public String toString() {return "Spear and shield";}},
    LIGHT_LANCE_SHIELD {public String toString() {return "Light lance and shield";}},
    LANCE_SHIELD {public String toString() {return "Lance and shield";}},
    UPROOTED_TREE {public String toString() {return "Uprooted tree";}},
    GIANT_CLUB {public String toString() {return "Giant club";}},
    TALON_SCYTHES {public String toString() {return "Talon scythes";}},
    EXECUTIONER_BLADE {public String toString() {return "Executioner's blade";}},
    NONE {public String toString() {return "None";}};

    public static WeaponType fromString(String text) {
        for (WeaponType value : WeaponType.values()) {
            if (value.toString().equals(text)) {
                return value;
            }
        }
        return null;
    }
}
