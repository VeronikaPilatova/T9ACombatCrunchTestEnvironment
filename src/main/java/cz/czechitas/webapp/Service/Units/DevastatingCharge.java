package cz.czechitas.webapp.Service.Units;

import java.util.*;

public class DevastatingCharge {

    private int att; //number of attacks
    private int off; //offensive skill
    private int str; //strength
    private int ap; //armor piercing
    private int agi; //agility/initiative
    private int def; //defense skill
    private int res; //resilience
    private int arm; //armor
    private int fortitudeSave;
    private int aegisSave;
    private int leadership;
    private int supportingRows;
    private List<SpecialRule> specialRulesArmybookEntry;
    private List<SpecialRule> specialRulesOffensiveProfile;

    public DevastatingCharge() {
        this.att = 0;
        this.off = 0;
        this.str = 0;
        this.ap = 0;
        this.agi = 0;
        this.def = 0;
        this.res = 0;
        this.arm = 0;
        this.fortitudeSave = 0;
        this.aegisSave = 0;
        this.leadership = 0;
        this.supportingRows = 0;
        this.specialRulesArmybookEntry = Arrays.asList();
        this.specialRulesOffensiveProfile = Arrays.asList();
    }

    public DevastatingCharge(int att, int off, int str, int ap, int agi, int def, int res, int arm, int fortitudeSave, int aegisSave, int leadership, int supportingRows, List<SpecialRule> specialRulesArmybookEntry, List<SpecialRule> specialRulesOffensiveProfile) {
        this.att = att;
        this.off = off;
        this.str = str;
        this.ap = ap;
        this.agi = agi;
        this.def = def;
        this.res = res;
        this.arm = arm;
        this.fortitudeSave = fortitudeSave;
        this.aegisSave = aegisSave;
        this.leadership = leadership;
        this.supportingRows = supportingRows;
        this.specialRulesArmybookEntry = specialRulesArmybookEntry;
        this.specialRulesOffensiveProfile = specialRulesOffensiveProfile;
    }

    public int getAtt() {
        return att;
    }

    public void setAtt(int newValue) {
        att = newValue;
    }

    public int getOff() {
        return off;
    }

    public void setOff(int newValue) {
        off = newValue;
    }

    public int getStr() {
        return str;
    }

    public void setStr(int newValue) {
        str = newValue;
    }

    public int getAp() {
        return ap;
    }

    public void setAp(int newValue) {
        ap = newValue;
    }

    public int getAgi() {
        return agi;
    }

    public void setAgi(int newValue) {
        agi = newValue;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int newValue) {
        def = newValue;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int newValue) {
        res = newValue;
    }

    public int getArm() {
        return arm;
    }

    public void setArm(int newValue) {
        arm = newValue;
    }

    public int getFortitudeSave() {
        return fortitudeSave;
    }

    public void setFortitudeSave(int newValue) {
        fortitudeSave = newValue;
    }

    public int getAegisSave() {
        return aegisSave;
    }

    public void setAegisSave(int newValue) {
        aegisSave = newValue;
    }

    public int getLeadership() {
        return leadership;
    }

    public void setLeadership(int newValue) {
        leadership = newValue;
    }

    public int getSupportingRows() {
        return supportingRows;
    }

    public void setSupportingRows(int newValue) {
        supportingRows = newValue;
    }

    public List<SpecialRule> getSpecialRulesArmybookEntry() {
        return specialRulesArmybookEntry;
    }

    public void setSpecialRulesArmybookEntry(List<SpecialRule> newValue) {
        specialRulesArmybookEntry = newValue;
    }

    public List<SpecialRule> getSpecialRulesOffensiveProfile() {
        return specialRulesOffensiveProfile;
    }

    public void setSpecialRulesOffensiveProfile(List<SpecialRule> newValue) {
        specialRulesOffensiveProfile = newValue;
    }
}
