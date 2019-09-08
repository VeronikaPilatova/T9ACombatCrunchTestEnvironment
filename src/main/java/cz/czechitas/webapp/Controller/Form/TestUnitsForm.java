package cz.czechitas.webapp.Controller.Form;

import java.util.*;
import cz.czechitas.webapp.Service.Units.*;

public class TestUnitsForm {

    private int u1_hp;
    private int u1_def;
    private int u1_res;
    private int u1_arm;
    private String u1_armorType;
    private int u1_fortitude;
    private int u1_aegis;
    private int u1_discipline;
    private int u1_baseWidth;
    private int u1_baseLength;
    private String u1_modelHeight;
    private String u1_modelType;
    private List<String> u1_specialRules = new ArrayList<>();

    private String op11_active;
    private int op11_attacks;
    private int op11_off;
    private int op11_str;
    private int op11_ap;
    private int op11_agi;
    private String op11_weapon;
    private String op11_multipleWounds;
    private String op11_impactHits;
    private String op11_stomp;
    private String op11_grindingHits;
    private List<String> op11_specialRules = new ArrayList<>();
    private int op11_repeated;

    private String op12_active;
    private int op12_attacks;
    private int op12_off;
    private int op12_str;
    private int op12_ap;
    private int op12_agi;
    private String op12_weapon;
    private String op12_multipleWounds;
    private String op12_impactHits;
    private String op12_stomp;
    private String op12_grindingHits;
    private List<String> op12_specialRules = new ArrayList<>();
    private int op12_repeated;

    private String op13_active;
    private int op13_attacks;
    private int op13_off;
    private int op13_str;
    private int op13_ap;
    private int op13_agi;
    private String op13_weapon;
    private String op13_multipleWounds;
    private String op13_impactHits;
    private String op13_stomp;
    private String op13_grindingHits;
    private List<String> op13_specialRules = new ArrayList<>();
    private int op13_repeated;

    private String op14_active;
    private int op14_attacks;
    private int op14_off;
    private int op14_str;
    private int op14_ap;
    private int op14_agi;
    private String op14_weapon;
    private String op14_multipleWounds;
    private String op14_impactHits;
    private String op14_stomp;
    private String op14_grindingHits;
    private List<String> op14_specialRules = new ArrayList<>();
    private int op14_repeated;

    private String op15_active;
    private int op15_attacks;
    private int op15_off;
    private int op15_str;
    private int op15_ap;
    private int op15_agi;
    private String op15_weapon;
    private String op15_multipleWounds;
    private String op15_impactHits;
    private String op15_stomp;
    private String op15_grindingHits;
    private List<String> op15_specialRules = new ArrayList<>();
    private int op15_repeated;

    private int u2_hp;
    private int u2_def;
    private int u2_res;
    private int u2_arm;
    private String u2_armorType;
    private int u2_fortitude;
    private int u2_aegis;
    private int u2_discipline;
    private int u2_baseWidth;
    private int u2_baseLength;
    private String u2_modelHeight;
    private String u2_modelType;
    private List<String> u2_specialRules = new ArrayList<>();

    private String op21_active;
    private int op21_attacks;
    private int op21_off;
    private int op21_str;
    private int op21_ap;
    private int op21_agi;
    private String op21_weapon;
    private String op21_multipleWounds;
    private String op21_impactHits;
    private String op21_stomp;
    private String op21_grindingHits;
    private List<String> op21_specialRules = new ArrayList<>();
    private int op21_repeated;

    private String op22_active;
    private int op22_attacks;
    private int op22_off;
    private int op22_str;
    private int op22_ap;
    private int op22_agi;
    private String op22_weapon;
    private String op22_multipleWounds;
    private String op22_impactHits;
    private String op22_stomp;
    private String op22_grindingHits;
    private List<String> op22_specialRules = new ArrayList<>();
    private int op22_repeated;

    private String op23_active;
    private int op23_attacks;
    private int op23_off;
    private int op23_str;
    private int op23_ap;
    private int op23_agi;
    private String op23_weapon;
    private String op23_multipleWounds;
    private String op23_impactHits;
    private String op23_stomp;
    private String op23_grindingHits;
    private List<String> op23_specialRules = new ArrayList<>();
    private int op23_repeated;

    private String op24_active;
    private int op24_attacks;
    private int op24_off;
    private int op24_str;
    private int op24_ap;
    private int op24_agi;
    private String op24_weapon;
    private String op24_multipleWounds;
    private String op24_impactHits;
    private String op24_stomp;
    private String op24_grindingHits;
    private List<String> op24_specialRules = new ArrayList<>();
    private int op24_repeated;

    private String op25_active;
    private int op25_attacks;
    private int op25_off;
    private int op25_str;
    private int op25_ap;
    private int op25_agi;
    private String op25_weapon;
    private String op25_multipleWounds;
    private String op25_impactHits;
    private String op25_stomp;
    private String op25_grindingHits;
    private List<String> op25_specialRules = new ArrayList<>();
    private int op25_repeated;

    private int u1_modelCount;
    private int u1_rowModels;
    private String u1_charge;
    private String u1_champion;
    private String u1_musician;
    private String u1_standard;
    private String u1_BSB;
    private String u1_general;
    private int u1_generalDis;

    private int u2_modelCount;
    private int u2_rowModels;
    private String u2_charge;
    private String u2_champion;
    private String u2_musician;
    private String u2_standard;
    private String u2_BSB;
    private String u2_general;
    private int u2_generalDis;

    public TestUnitsForm() {
    }


    public int getU1_hp() {
        return u1_hp;
    }

    public void setU1_hp(int newValue) {
        u1_hp = newValue;
    }

    public int getU1_def() {
        return u1_def;
    }

    public void setU1_def(int newValue) {
        u1_def = newValue;
    }

    public int getU1_res() {
        return u1_res;
    }

    public void setU1_res(int newValue) {
        u1_res = newValue;
    }

    public int getU1_arm() {
        return u1_arm;
    }

    public void setU1_arm(int newValue) {
        u1_arm = newValue;
    }

    public int getU1_fortitude() {
        return u1_fortitude;
    }

    public void setU1_fortitude(int newValue) {
        u1_fortitude = newValue;
    }

    public int getU1_aegis() {
        return u1_aegis;
    }

    public void setU1_aegis(int newValue) {
        u1_aegis = newValue;
    }

    public int getU1_discipline() {
        return u1_discipline;
    }

    public void setU1_discipline(int newValue) {
        u1_discipline = newValue;
    }

    public int getU1_baseWidth() {
        return u1_baseWidth;
    }

    public void setU1_baseWidth(int newValue) {
        u1_baseWidth = newValue;
    }

    public int getU1_baseLength() {
        return u1_baseLength;
    }

    public void setU1_baseLength(int newValue) {
        u1_baseLength = newValue;
    }

    public ModelHeight getU1_modelHeight() {
        switch (u1_modelHeight) {
            case "Standard":
                return ModelHeight.STANDARD;
            case "Large":
                return ModelHeight.LARGE;
            case "Gigantic":
                return ModelHeight.GIGANTIC;
        }
        return ModelHeight.STANDARD;
    }

    public void setU1_modelHeight(String newValue) {
        u1_modelHeight = newValue;
    }

    public ModelType getU1_modelType() {
        switch (u1_modelType) {
            case "Infantry":
                return ModelType.INFANTRY;
            case "Cavalry":
                return ModelType.CAVALRY;
            case "Beast":
                return ModelType.BEAST;
            case "Construct":
                return ModelType.CONSTRUCT;
        }
        return ModelType.INFANTRY;
    }

    public void setU1_modelType(String newValue) {
        u1_modelType = newValue;
    }

    public int getOp11_attacks() {
        return op11_attacks;
    }

    public void setOp11_attacks(int newValue) {
        op11_attacks = newValue;
    }

    public int getOp11_off() {
        return op11_off;
    }

    public void setOp11_off(int newValue) {
        op11_off = newValue;
    }

    public int getOp11_str() {
        return op11_str;
    }

    public void setOp11_str(int newValue) {
        op11_str = newValue;
    }

    public int getOp11_ap() {
        return op11_ap;
    }

    public void setOp11_ap(int newValue) {
        op11_ap = newValue;
    }

    public int getOp11_agi() {
        return op11_agi;
    }

    public void setOp11_agi(int newValue) {
        op11_agi = newValue;
    }

    public int getU2_hp() {
        return u2_hp;
    }

    public void setU2_hp(int newValue) {
        u2_hp = newValue;
    }

    public int getU2_def() {
        return u2_def;
    }

    public void setU2_def(int newValue) {
        u2_def = newValue;
    }

    public int getU2_res() {
        return u2_res;
    }

    public void setU2_res(int newValue) {
        u2_res = newValue;
    }

    public int getU2_arm() {
        return u2_arm;
    }

    public void setU2_arm(int newValue) {
        u2_arm = newValue;
    }

    public int getU2_fortitude() {
        return u2_fortitude;
    }

    public void setU2_fortitude(int newValue) {
        u2_fortitude = newValue;
    }

    public int getU2_aegis() {
        return u2_aegis;
    }

    public void setU2_aegis(int newValue) {
        u2_aegis = newValue;
    }

    public int getU2_discipline() {
        return u2_discipline;
    }

    public void setU2_discipline(int newValue) {
        u2_discipline = newValue;
    }

    public int getU2_baseWidth() {
        return u2_baseWidth;
    }

    public void setU2_baseWidth(int newValue) {
        u2_baseWidth = newValue;
    }

    public int getU2_baseLength() {
        return u2_baseLength;
    }

    public void setU2_baseLength(int newValue) {
        u2_baseLength = newValue;
    }

    public ModelHeight getU2_modelHeight() {
        switch (u2_modelHeight) {
            case "Standard":
                return ModelHeight.STANDARD;
            case "Large":
                return ModelHeight.LARGE;
            case "Gigantic":
                return ModelHeight.GIGANTIC;
        }
        return ModelHeight.STANDARD;
    }

    public void setU2_modelHeight(String newValue) {
        u2_modelHeight = newValue;
    }

    public ModelType getU2_modelType() {
        switch (u2_modelType) {
            case "Infantry":
                return ModelType.INFANTRY;
            case "Cavalry":
                return ModelType.CAVALRY;
            case "Beast":
                return ModelType.BEAST;
            case "Construct":
                return ModelType.CONSTRUCT;
        }
        return ModelType.INFANTRY;
    }

    public void setU2_modelType(String newValue) {
        u2_modelType = newValue;
    }

    public boolean getOp11_active() {
        return Objects.equals(op11_active, "TRUE");
    }

    public void setOp11_active(String newValue) {
        op11_active = newValue;
    }

    public boolean getOp21_active() {
        return Objects.equals(op21_active, "TRUE");
    }

    public void setOp21_active(String newValue) {
        op21_active = newValue;
    }

    public int getOp21_attacks() {
        return op21_attacks;
    }

    public void setOp21_attacks(int newValue) {
        op21_attacks = newValue;
    }

    public int getOp21_off() {
        return op21_off;
    }

    public void setOp21_off(int newValue) {
        op21_off = newValue;
    }

    public int getOp21_str() {
        return op21_str;
    }

    public void setOp21_str(int newValue) {
        op21_str = newValue;
    }

    public int getOp21_ap() {
        return op21_ap;
    }

    public void setOp21_ap(int newValue) {
        op21_ap = newValue;
    }

    public int getOp21_agi() {
        return op21_agi;
    }

    public void setOp21_agi(int newValue) {
        op21_agi = newValue;
    }

    public ArmorType getU1_armorType() {
        switch (u1_armorType) {
            case "None":
                return ArmorType.NONE;
            case "Light":
                return ArmorType.LIGHT;
            case "Heavy":
                return ArmorType.HEAVY;
            case "Plate":
                return ArmorType.PLATE;
        }
        return ArmorType.NONE;
    }

    public void setU1_armorType(String newValue) {
        u1_armorType = newValue;
    }

    public ArmorType getU2_armorType() {
        switch (u2_armorType) {
            case "None":
                return ArmorType.NONE;
            case "Light":
                return ArmorType.LIGHT;
            case "Heavy":
                return ArmorType.HEAVY;
            case "Plate":
                return ArmorType.PLATE;
        }
        return ArmorType.NONE;
    }

    public void setU2_armorType(String newValue) {
        u2_armorType = newValue;
    }

    public int getOp11_repeated() {
        return op11_repeated;
    }

    public void setOp11_repeated(int newValue) {
        op11_repeated = newValue;
    }

    public int getOp21_repeated() {
        return op21_repeated;
    }

    public void setOp21_repeated(int newValue) {
        op21_repeated = newValue;
    }

    public WeaponType getOp11_weapon() {
        return WeaponType.fromString(op11_weapon);
    }

    public void setOp11_weapon(String newValue) {
        op11_weapon = newValue;
    }

    public WeaponType getOp21_weapon() {
        return WeaponType.fromString(op21_weapon);
    }

    public void setOp21_weapon(String newValue) {
        op21_weapon = newValue;
    }

    public int getOp12_attacks() {
        return op12_attacks;
    }

    public void setOp12_attacks(int newValue) {
        op12_attacks = newValue;
    }

    public int getOp12_off() {
        return op12_off;
    }

    public void setOp12_off(int newValue) {
        op12_off = newValue;
    }

    public int getOp12_str() {
        return op12_str;
    }

    public void setOp12_str(int newValue) {
        op12_str = newValue;
    }

    public int getOp12_ap() {
        return op12_ap;
    }

    public void setOp12_ap(int newValue) {
        op12_ap = newValue;
    }

    public int getOp12_agi() {
        return op12_agi;
    }

    public void setOp12_agi(int newValue) {
        op12_agi = newValue;
    }

    public int getOp12_repeated() {
        return op12_repeated;
    }

    public void setOp12_repeated(int newValue) {
        op12_repeated = newValue;
    }

    public int getOp13_attacks() {
        return op13_attacks;
    }

    public void setOp13_attacks(int newValue) {
        op13_attacks = newValue;
    }

    public int getOp13_off() {
        return op13_off;
    }

    public void setOp13_off(int newValue) {
        op13_off = newValue;
    }

    public int getOp13_str() {
        return op13_str;
    }

    public void setOp13_str(int newValue) {
        op13_str = newValue;
    }

    public int getOp13_ap() {
        return op13_ap;
    }

    public void setOp13_ap(int newValue) {
        op13_ap = newValue;
    }

    public int getOp13_agi() {
        return op13_agi;
    }

    public void setOp13_agi(int newValue) {
        op13_agi = newValue;
    }

    public int getOp13_repeated() {
        return op13_repeated;
    }

    public void setOp13_repeated(int newValue) {
        op13_repeated = newValue;
    }

    public int getOp14_attacks() {
        return op14_attacks;
    }

    public void setOp14_attacks(int newValue) {
        op14_attacks = newValue;
    }

    public int getOp14_off() {
        return op14_off;
    }

    public void setOp14_off(int newValue) {
        op14_off = newValue;
    }

    public int getOp14_str() {
        return op14_str;
    }

    public void setOp14_str(int newValue) {
        op14_str = newValue;
    }

    public int getOp14_ap() {
        return op14_ap;
    }

    public void setOp14_ap(int newValue) {
        op14_ap = newValue;
    }

    public int getOp14_agi() {
        return op14_agi;
    }

    public void setOp14_agi(int newValue) {
        op14_agi = newValue;
    }

    public int getOp14_repeated() {
        return op14_repeated;
    }

    public void setOp14_repeated(int newValue) {
        op14_repeated = newValue;
    }

    public int getOp15_attacks() {
        return op15_attacks;
    }

    public void setOp15_attacks(int newValue) {
        op15_attacks = newValue;
    }

    public int getOp15_off() {
        return op15_off;
    }

    public void setOp15_off(int newValue) {
        op15_off = newValue;
    }

    public int getOp15_str() {
        return op15_str;
    }

    public void setOp15_str(int newValue) {
        op15_str = newValue;
    }

    public int getOp15_ap() {
        return op15_ap;
    }

    public void setOp15_ap(int newValue) {
        op15_ap = newValue;
    }

    public int getOp15_agi() {
        return op15_agi;
    }

    public void setOp15_agi(int newValue) {
        op15_agi = newValue;
    }

    public int getOp15_repeated() {
        return op15_repeated;
    }

    public void setOp15_repeated(int newValue) {
        op15_repeated = newValue;
    }

    public int getOp22_attacks() {
        return op22_attacks;
    }

    public void setOp22_attacks(int newValue) {
        op22_attacks = newValue;
    }

    public int getOp22_off() {
        return op22_off;
    }

    public void setOp22_off(int newValue) {
        op22_off = newValue;
    }

    public int getOp22_str() {
        return op22_str;
    }

    public void setOp22_str(int newValue) {
        op22_str = newValue;
    }

    public int getOp22_ap() {
        return op22_ap;
    }

    public void setOp22_ap(int newValue) {
        op22_ap = newValue;
    }

    public int getOp22_agi() {
        return op22_agi;
    }

    public void setOp22_agi(int newValue) {
        op22_agi = newValue;
    }

    public int getOp22_repeated() {
        return op22_repeated;
    }

    public void setOp22_repeated(int newValue) {
        op22_repeated = newValue;
    }

    public int getOp23_attacks() {
        return op23_attacks;
    }

    public void setOp23_attacks(int newValue) {
        op23_attacks = newValue;
    }

    public int getOp23_off() {
        return op23_off;
    }

    public void setOp23_off(int newValue) {
        op23_off = newValue;
    }

    public int getOp23_str() {
        return op23_str;
    }

    public void setOp23_str(int newValue) {
        op23_str = newValue;
    }

    public int getOp23_ap() {
        return op23_ap;
    }

    public void setOp23_ap(int newValue) {
        op23_ap = newValue;
    }

    public int getOp23_agi() {
        return op23_agi;
    }

    public void setOp23_agi(int newValue) {
        op23_agi = newValue;
    }

    public int getOp23_repeated() {
        return op23_repeated;
    }

    public void setOp23_repeated(int newValue) {
        op23_repeated = newValue;
    }

    public int getOp24_attacks() {
        return op24_attacks;
    }

    public void setOp24_attacks(int newValue) {
        op24_attacks = newValue;
    }

    public int getOp24_off() {
        return op24_off;
    }

    public void setOp24_off(int newValue) {
        op24_off = newValue;
    }

    public int getOp24_str() {
        return op24_str;
    }

    public void setOp24_str(int newValue) {
        op24_str = newValue;
    }

    public int getOp24_ap() {
        return op24_ap;
    }

    public void setOp24_ap(int newValue) {
        op24_ap = newValue;
    }

    public int getOp24_agi() {
        return op24_agi;
    }

    public void setOp24_agi(int newValue) {
        op24_agi = newValue;
    }

    public int getOp24_repeated() {
        return op24_repeated;
    }

    public void setOp24_repeated(int newValue) {
        op24_repeated = newValue;
    }

    public int getOp25_attacks() {
        return op25_attacks;
    }

    public void setOp25_attacks(int newValue) {
        op25_attacks = newValue;
    }

    public int getOp25_off() {
        return op25_off;
    }

    public void setOp25_off(int newValue) {
        op25_off = newValue;
    }

    public int getOp25_str() {
        return op25_str;
    }

    public void setOp25_str(int newValue) {
        op25_str = newValue;
    }

    public int getOp25_ap() {
        return op25_ap;
    }

    public void setOp25_ap(int newValue) {
        op25_ap = newValue;
    }

    public int getOp25_agi() {
        return op25_agi;
    }

    public void setOp25_agi(int newValue) {
        op25_agi = newValue;
    }

    public int getOp25_repeated() {
        return op25_repeated;
    }

    public void setOp25_repeated(int newValue) {
        op25_repeated = newValue;
    }

    public WeaponType getOp12_weapon() {
        return WeaponType.fromString(op12_weapon);
    }

    public void setOp12_weapon(String newValue) {
        op12_weapon = newValue;
    }

    public WeaponType getOp13_weapon() {
        return WeaponType.fromString(op13_weapon);
    }

    public void setOp13_weapon(String newValue) {
        op13_weapon = newValue;
    }

    public WeaponType getOp14_weapon() {
        return WeaponType.fromString(op14_weapon);
    }

    public void setOp14_weapon(String newValue) {
        op14_weapon = newValue;
    }

    public WeaponType getOp15_weapon() {
        return WeaponType.fromString(op15_weapon);
    }

    public void setOp15_weapon(String newValue) {
        op15_weapon = newValue;
    }

    public WeaponType getOp22_weapon() {
        return WeaponType.fromString(op22_weapon);
    }

    public void setOp22_weapon(String newValue) {
        op22_weapon = newValue;
    }

    public WeaponType getOp23_weapon() {
        return WeaponType.fromString(op23_weapon);
    }

    public void setOp23_weapon(String newValue) {
        op23_weapon = newValue;
    }

    public WeaponType getOp24_weapon() {
        return WeaponType.fromString(op24_weapon);
    }

    public void setOp24_weapon(String newValue) {
        op24_weapon = newValue;
    }

    public WeaponType getOp25_weapon() {
        return WeaponType.fromString(op25_weapon);
    }

    public void setOp25_weapon(String newValue) {
        op25_weapon = newValue;
    }

    public boolean getOp12_active() {
        return Objects.equals(op12_active, "TRUE");
    }

    public void setOp12_active(String newValue) {
        op12_active = newValue;
    }

    public boolean getOp13_active() {
        return Objects.equals(op13_active, "TRUE");
    }

    public void setOp13_active(String newValue) {
        op13_active = newValue;
    }

    public boolean getOp14_active() {
        return Objects.equals(op14_active, "TRUE");
    }

    public void setOp14_active(String newValue) {
        op14_active = newValue;
    }

    public boolean getOp15_active() {
        return Objects.equals(op15_active, "TRUE");
    }

    public void setOp15_active(String newValue) {
        op15_active = newValue;
    }

    public boolean getOp22_active() {
        return Objects.equals(op22_active, "TRUE");
    }

    public void setOp22_active(String newValue) {
        op22_active = newValue;
    }

    public boolean getOp23_active() {
        return Objects.equals(op23_active, "TRUE");
    }

    public void setOp23_active(String newValue) {
        op23_active = newValue;
    }

    public boolean getOp24_active() {
        return Objects.equals(op24_active, "TRUE");
    }

    public void setOp24_active(String newValue) {
        op24_active = newValue;
    }

    public boolean getOp25_active() {
        return Objects.equals(op25_active, "TRUE");
    }

    public void setOp25_active(String newValue) {
        op25_active = newValue;
    }

    public Dice getOp11_multipleWounds() {
        return Dice.fromString(op11_multipleWounds);
    }

    public void setOp11_multipleWounds(String newValue) {
        op11_multipleWounds = newValue;
    }

    public Dice getOp11_impactHits() {
        return Dice.fromString(op11_impactHits);
    }

    public void setOp11_impactHits(String newValue) {
        op11_impactHits = newValue;
    }

    public Dice getOp11_stomp() {
        return Dice.fromString(op11_stomp);
    }

    public void setOp11_stomp(String newValue) {
        op11_stomp = newValue;
    }

    public Dice getOp11_grindingHits() {
        return Dice.fromString(op11_grindingHits);
    }

    public void setOp11_grindingHits(String newValue) {
        op11_grindingHits = newValue;
    }

    public Dice getOp12_multipleWounds() {
        return Dice.fromString(op12_multipleWounds);
    }

    public void setOp12_multipleWounds(String newValue) {
        op12_multipleWounds = newValue;
    }

    public Dice getOp12_impactHits() {
        return Dice.fromString(op12_impactHits);
    }

    public void setOp12_impactHits(String newValue) {
        op12_impactHits = newValue;
    }

    public Dice getOp12_stomp() {
        return Dice.fromString(op12_stomp);
    }

    public void setOp12_stomp(String newValue) {
        op12_stomp = newValue;
    }

    public Dice getOp12_grindingHits() {
        return Dice.fromString(op12_grindingHits);
    }

    public void setOp12_grindingHits(String newValue) {
        op12_grindingHits = newValue;
    }

    public Dice getOp13_multipleWounds() {
        return Dice.fromString(op13_multipleWounds);
    }

    public void setOp13_multipleWounds(String newValue) {
        op13_multipleWounds = newValue;
    }

    public Dice getOp13_impactHits() {
        return Dice.fromString(op13_impactHits);
    }

    public void setOp13_impactHits(String newValue) {
        op13_impactHits = newValue;
    }

    public Dice getOp13_stomp() {
        return Dice.fromString(op13_stomp);
    }

    public void setOp13_stomp(String newValue) {
        op13_stomp = newValue;
    }

    public Dice getOp13_grindingHits() {
        return Dice.fromString(op13_grindingHits);
    }

    public void setOp13_grindingHits(String newValue) {
        op13_grindingHits = newValue;
    }

    public Dice getOp14_multipleWounds() {
        return Dice.fromString(op14_multipleWounds);
    }

    public void setOp14_multipleWounds(String newValue) {
        op14_multipleWounds = newValue;
    }

    public Dice getOp14_impactHits() {
        return Dice.fromString(op14_impactHits);
    }

    public void setOp14_impactHits(String newValue) {
        op14_impactHits = newValue;
    }

    public Dice getOp14_stomp() {
        return Dice.fromString(op14_stomp);
    }

    public void setOp14_stomp(String newValue) {
        op14_stomp = newValue;
    }

    public Dice getOp14_grindingHits() {
        return Dice.fromString(op14_grindingHits);
    }

    public void setOp14_grindingHits(String newValue) {
        op14_grindingHits = newValue;
    }

    public Dice getOp15_multipleWounds() {
        return Dice.fromString(op15_multipleWounds);
    }

    public void setOp15_multipleWounds(String newValue) {
        op15_multipleWounds = newValue;
    }

    public Dice getOp15_impactHits() {
        return Dice.fromString(op15_impactHits);
    }

    public void setOp15_impactHits(String newValue) {
        op15_impactHits = newValue;
    }

    public Dice getOp15_stomp() {
        return Dice.fromString(op15_stomp);
    }

    public void setOp15_stomp(String newValue) {
        op15_stomp = newValue;
    }

    public Dice getOp15_grindingHits() {
        return Dice.fromString(op15_grindingHits);
    }

    public void setOp15_grindingHits(String newValue) {
        op15_grindingHits = newValue;
    }

    public Dice getOp21_multipleWounds() {
        return Dice.fromString(op21_multipleWounds);
    }

    public void setOp21_multipleWounds(String newValue) {
        op21_multipleWounds = newValue;
    }

    public Dice getOp21_impactHits() {
        return Dice.fromString(op21_impactHits);
    }

    public void setOp21_impactHits(String newValue) {
        op21_impactHits = newValue;
    }

    public Dice getOp21_stomp() {
        return Dice.fromString(op21_stomp);
    }

    public void setOp21_stomp(String newValue) {
        op21_stomp = newValue;
    }

    public Dice getOp21_grindingHits() {
        return Dice.fromString(op21_grindingHits);
    }

    public void setOp21_grindingHits(String newValue) {
        op21_grindingHits = newValue;
    }

    public Dice getOp22_multipleWounds() {
        return Dice.fromString(op22_multipleWounds);
    }

    public void setOp22_multipleWounds(String newValue) {
        op22_multipleWounds = newValue;
    }

    public Dice getOp22_impactHits() {
        return Dice.fromString(op22_impactHits);
    }

    public void setOp22_impactHits(String newValue) {
        op22_impactHits = newValue;
    }

    public Dice getOp22_stomp() {
        return Dice.fromString(op22_stomp);
    }

    public void setOp22_stomp(String newValue) {
        op22_stomp = newValue;
    }

    public Dice getOp22_grindingHits() {
        return Dice.fromString(op22_grindingHits);
    }

    public void setOp22_grindingHits(String newValue) {
        op22_grindingHits = newValue;
    }

    public Dice getOp23_multipleWounds() {
        return Dice.fromString(op23_multipleWounds);
    }

    public void setOp23_multipleWounds(String newValue) {
        op23_multipleWounds = newValue;
    }

    public Dice getOp23_impactHits() {
        return Dice.fromString(op23_impactHits);
    }

    public void setOp23_impactHits(String newValue) {
        op23_impactHits = newValue;
    }

    public Dice getOp23_stomp() {
        return Dice.fromString(op23_stomp);
    }

    public void setOp23_stomp(String newValue) {
        op23_stomp = newValue;
    }

    public Dice getOp23_grindingHits() {
        return Dice.fromString(op23_grindingHits);
    }

    public void setOp23_grindingHits(String newValue) {
        op23_grindingHits = newValue;
    }

    public Dice getOp24_multipleWounds() {
        return Dice.fromString(op24_multipleWounds);
    }

    public void setOp24_multipleWounds(String newValue) {
        op24_multipleWounds = newValue;
    }

    public Dice getOp24_impactHits() {
        return Dice.fromString(op24_impactHits);
    }

    public void setOp24_impactHits(String newValue) {
        op24_impactHits = newValue;
    }

    public Dice getOp24_stomp() {
        return Dice.fromString(op24_stomp);
    }

    public void setOp24_stomp(String newValue) {
        op24_stomp = newValue;
    }

    public Dice getOp24_grindingHits() {
        return Dice.fromString(op24_grindingHits);
    }

    public void setOp24_grindingHits(String newValue) {
        op24_grindingHits = newValue;
    }

    public Dice getOp25_multipleWounds() {
        return Dice.fromString(op25_multipleWounds);
    }

    public void setOp25_multipleWounds(String newValue) {
        op25_multipleWounds = newValue;
    }

    public Dice getOp25_impactHits() {
        return Dice.fromString(op25_impactHits);
    }

    public void setOp25_impactHits(String newValue) {
        op25_impactHits = newValue;
    }

    public Dice getOp25_stomp() {
        return Dice.fromString(op25_stomp);
    }

    public void setOp25_stomp(String newValue) {
        op25_stomp = newValue;
    }

    public Dice getOp25_grindingHits() {
        return Dice.fromString(op25_grindingHits);
    }

    public void setOp25_grindingHits(String newValue) {
        op25_grindingHits = newValue;
    }

    public int getU1_modelCount() {
        return u1_modelCount;
    }

    public void setU1_modelCount(int newValue) {
        u1_modelCount = newValue;
    }

    public int getU1_rowModels() {
        return u1_rowModels;
    }

    public void setU1_rowModels(int newValue) {
        u1_rowModels = newValue;
    }

    public int getU2_modelCount() {
        return u2_modelCount;
    }

    public void setU2_modelCount(int newValue) {
        u2_modelCount = newValue;
    }

    public int getU2_rowModels() {
        return u2_rowModels;
    }

    public void setU2_rowModels(int newValue) {
        u2_rowModels = newValue;
    }

    public int getU1_charge() {
        if (Objects.equals(u1_charge, "TRUE")) {
            return 1;
        } else {
            return 0;
        }
    }

    public void setU1_charge(String newValue) {
        u1_charge = newValue;
    }

    public int getU2_charge() {
        if (Objects.equals(u2_charge, "TRUE")) {
            return 1;
        } else {
            return 0;
        }
    }

    public void setU2_charge(String newValue) {
        u2_charge = newValue;
    }

    public List<String> getU1_specialRules() {
        return u1_specialRules;
    }

    public void setU1_specialRules(List<String> newValue) {
        u1_specialRules = newValue;
    }

    public List<String> getOp11_specialRules() {
        return op11_specialRules;
    }

    public void setOp11_specialRules(List<String> newValue) {
        op11_specialRules = newValue;
    }

    public List<String> getOp12_specialRules() {
        return op12_specialRules;
    }

    public void setOp12_specialRules(List<String> newValue) {
        op12_specialRules = newValue;
    }

    public List<String> getOp13_specialRules() {
        return op13_specialRules;
    }

    public void setOp13_specialRules(List<String> newValue) {
        op13_specialRules = newValue;
    }

    public List<String> getOp14_specialRules() {
        return op14_specialRules;
    }

    public void setOp14_specialRules(List<String> newValue) {
        op14_specialRules = newValue;
    }

    public List<String> getOp15_specialRules() {
        return op15_specialRules;
    }

    public void setOp15_specialRules(List<String> newValue) {
        op15_specialRules = newValue;
    }

    public List<String> getU2_specialRules() {
        return u2_specialRules;
    }

    public void setU2_specialRules(List<String> newValue) {
        u2_specialRules = newValue;
    }

    public List<String> getOp21_specialRules() {
        return op21_specialRules;
    }

    public void setOp21_specialRules(List<String> newValue) {
        op21_specialRules = newValue;
    }

    public List<String> getOp22_specialRules() {
        return op22_specialRules;
    }

    public void setOp22_specialRules(List<String> newValue) {
        op22_specialRules = newValue;
    }

    public List<String> getOp23_specialRules() {
        return op23_specialRules;
    }

    public void setOp23_specialRules(List<String> newValue) {
        op23_specialRules = newValue;
    }

    public List<String> getOp24_specialRules() {
        return op24_specialRules;
    }

    public void setOp24_specialRules(List<String> newValue) {
        op24_specialRules = newValue;
    }

    public List<String> getOp25_specialRules() {
        return op25_specialRules;
    }

    public void setOp25_specialRules(List<String> newValue) {
        op25_specialRules = newValue;
    }

    public int getU1_generalDis() {
        return u1_generalDis;
    }

    public void setU1_generalDis(int newValue) {
        u1_generalDis = newValue;
    }

    public int getU2_generalDis() {
        return u2_generalDis;
    }

    public void setU2_generalDis(int newValue) {
        u2_generalDis = newValue;
    }

    public int getU1_champion() {
        if (Objects.equals(u1_champion, "TRUE")) {
            return 1;
        } else {
            return 0;
        }
    }

    public void setU1_champion(String newValue) {
        u1_champion = newValue;
    }

    public int getU1_musician() {
        if (Objects.equals(u1_musician, "TRUE")) {
            return 1;
        } else {
            return 0;
        }
    }

    public void setU1_musician(String newValue) {
        u1_musician = newValue;
    }

    public int getU1_standard() {
        if (Objects.equals(u1_standard, "TRUE")) {
            return 1;
        } else {
            return 0;
        }
    }

    public void setU1_standard(String newValue) {
        u1_standard = newValue;
    }

    public int getU1_BSB() {
        if (Objects.equals(u1_BSB, "TRUE")) {
            return 1;
        } else {
            return 0;
        }
    }

    public void setU1_BSB(String newValue) {
        u1_BSB = newValue;
    }

    public int getU1_general() {
        if (Objects.equals(u1_general, "TRUE")) {
            return 1;
        } else {
            return 0;
        }
    }

    public void setU1_general(String newValue) {
        u1_general = newValue;
    }

    public int getU2_champion() {
        if (Objects.equals(u2_champion, "TRUE")) {
            return 1;
        } else {
            return 0;
        }
    }

    public void setU2_champion(String newValue) {
        u2_champion = newValue;
    }

    public int getU2_musician() {
        if (Objects.equals(u2_musician, "TRUE")) {
            return 1;
        } else {
            return 0;
        }
    }

    public void setU2_musician(String newValue) {
        u2_musician = newValue;
    }

    public int getU2_standard() {
        if (Objects.equals(u2_standard, "TRUE")) {
            return 1;
        } else {
            return 0;
        }
    }

    public void setU2_standard(String newValue) {
        u2_standard = newValue;
    }

    public int getU2_BSB() {
        if (Objects.equals(u2_BSB, "TRUE")) {
            return 1;
        } else {
            return 0;
        }
    }

    public void setU2_BSB(String newValue) {
        u2_BSB = newValue;
    }

    public int getU2_general() {
        if (Objects.equals(u2_general, "TRUE")) {
            return 1;
        } else {
            return 0;
        }
    }

    public void setU2_general(String newValue) {
        u2_general = newValue;
    }
}
