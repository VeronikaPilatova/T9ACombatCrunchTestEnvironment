package cz.czechitas.webapp.Service.Units;

import java.util.*;

public class ArmybookEntry {
    protected String name;
    protected int hp;
    protected int def; //defense skill
    protected int res; //resilience
    protected int arm; //armor
    protected ArmorType armorType;
    protected int fortitudeSave;
    protected int aegisSave;
    protected String adv;
    protected String mar;
    protected int leadership;
    protected int baseWidth;
    protected int baseLength;
    protected ModelHeight modelHeight;
    protected ModelType modelType;
    protected boolean onFoot;
    protected int supportingRows;
    protected int minModels;
    protected int maxModels;
    protected int championPossible;
    protected int musicianPossible;
    protected int standardPossible;
    protected List<OffensiveProfile> offensiveProfiles;
    protected List<Integer> offensiveProfileRepeat;
    protected List<Integer> championApplicableList;
    protected List<Integer> stompApplicableList;
    protected List<List<WeaponType>> possibleWeaponsList;
    protected List<List<WeaponTypeShooting>> possibleShootingWeaponsList;
    protected Armybook armybook;
    protected List<SpecialRule> specialRules;
    protected List<SpecialRule> optionalRules;

    public ArmybookEntry() {
    }

    public ArmybookEntry(String name, int hp, int def, int res, int arm, ArmorType armorType, int fortitudeSave, int aegisSave, String adv, String mar, int leadership,
                         int baseWidth, int baseLength, ModelHeight modelHeight, ModelType modelType, boolean onFoot, int supportingRows,
                         int minModels, int maxModels, int championPossible, int musicianPossible, int standardPossible,
                         List<OffensiveProfile> offensiveProfiles, List<Integer> offensiveProfileRepeat, List<Integer> championApplicableList, List<Integer> stompApplicableList,
                         List<List<WeaponType>> possibleWeaponsList, List<List<WeaponTypeShooting>> possibleShootingWeaponsList,
                         Armybook armybook, List specialRules, List optionalRules) {
        this.name = name;
        this.hp = hp;
        this.def = def;
        this.res = res;
        this.arm = arm;
        this.armorType = armorType;
        this.fortitudeSave = fortitudeSave;
        this.aegisSave = aegisSave;
        this.adv = adv;
        this.mar = mar;
        this.leadership = leadership;
        this.baseWidth = baseWidth;
        this.baseLength = baseLength;
        this.modelHeight = modelHeight;
        this.onFoot = onFoot;
        this.modelType = modelType;
        this.supportingRows = supportingRows;
        this.minModels = minModels;
        this.maxModels = maxModels;
        this.championPossible = championPossible;
        this.musicianPossible = musicianPossible;
        this.standardPossible = standardPossible;
        this.offensiveProfiles = offensiveProfiles;
        this.offensiveProfileRepeat = offensiveProfileRepeat;
        this.championApplicableList = championApplicableList;
        this.stompApplicableList = stompApplicableList;
        this.possibleWeaponsList = possibleWeaponsList;
        this.possibleShootingWeaponsList = possibleShootingWeaponsList;
        this.armybook = armybook;
        this.specialRules = specialRules;
        this.optionalRules = optionalRules;
    }

    public String getName() {
        return name;
    }

    public void setName(String newValue) {
        name = newValue;
    }

    public int getHp() {
        if (specialRules.contains(SpecialRule.BIG_BROTHER)) {
            return 8;
        } else {
            return hp;
        }
    }

    public void setHp(int newValue) {
        hp = newValue;
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

    public int getBaseWidth() {
        if (specialRules.contains(SpecialRule.BIG_BROTHER)) {
            return 75;
        } else {
            return baseWidth;
        }
    }

    public void setBaseWidth(int newValue) {
        baseWidth = newValue;
    }

    public int getBaseLength() {
        if (specialRules.contains(SpecialRule.BIG_BROTHER)) {
            return 100;
        } else {
            return baseLength;
        }
    }

    public void setBaseLength(int newValue) {
        baseLength = newValue;
    }

    public List<OffensiveProfile> getOffensiveProfiles() {
        return offensiveProfiles;
    }

    public void setOffensiveProfiles(List<OffensiveProfile> newValue) { offensiveProfiles = newValue; }

    public List<Integer> getOffensiveProfileRepeat() {
        return offensiveProfileRepeat;
    }

    public void setOffensiveProfileRepeat(List<Integer> newValue) { offensiveProfileRepeat = newValue; }

    public Armybook getArmybook() {
        return armybook;
    }

    public void setArmybook(Armybook newValue) {
        armybook = newValue;
    }

    public int getSupportingRows() {
        return supportingRows;
    }

    public void setSupportingRows(int newValue) {
        supportingRows = newValue;
    }

    public int getMinimumRankModels() {
        if (modelHeight == ModelHeight.STANDARD) {
            return 5;
        } else if (modelHeight == ModelHeight.LARGE) {
            return 3;
        } else {
            return 1;
        }
    }

    public int getStompable() {
        if (modelHeight == ModelHeight.STANDARD && modelType != ModelType.CAVALRY) {
            return 1;
        } else {
            return 0;
        }
    }

    public ModelHeight getModelHeight() { return modelHeight; }

    public void setModelHeight(ModelHeight modelHeight) { this.modelHeight = modelHeight; }

    public ModelType getModelType() { return modelType; }

    public void setModelType(ModelType modelType) { this.modelType = modelType; }

    public boolean isOnFoot() { return onFoot; }

    public void setOnFoot(boolean onFoot) { this.onFoot = onFoot; }

    public int getMinModels() { return minModels; }

    public void setMinModels(int minModels) { this.minModels = minModels; }

    public int getMaxModels() { return maxModels; }

    public void setMaxModels(int maxModels) { this.maxModels = maxModels; }

    public int getChampionPossible() { return championPossible; }

    public void setChampionPossible(int championPossible) { this.championPossible = championPossible; }

    public int getMusicianPossible() { return musicianPossible; }

    public void setMusicianPossible(int musicianPossible) { this.musicianPossible = musicianPossible; }

    public int getStandardPossible() { return standardPossible; }

    public void setStandardPossible(int standardPossible) { this.standardPossible = standardPossible; }

    public List<List<WeaponType>> getPossibleWeaponsList() {
        return possibleWeaponsList;
    }

    public void setPossibleWeaponsList(List<List<WeaponType>> newValue) { possibleWeaponsList = newValue; }

    public List<List<WeaponTypeShooting>> getPossibleShootingWeaponsList() { return possibleShootingWeaponsList; }

    public void setPossibleShootingWeaponsList(List<List<WeaponTypeShooting>> newValue) { possibleShootingWeaponsList = newValue; }

    public List<Integer> getChampionApplicableList() {
        return championApplicableList;
    }

    public void setChampionApplicableList(List<Integer> newValue) { championApplicableList = newValue; }

    public List getSpecialRules() {
        return specialRules;
    }

    public void setSpecialRules(List newValue) {
        specialRules = newValue;
    }

    public ArmorType getArmorType() {
        return armorType;
    }

    public void setArmorType(ArmorType armorType) {
        this.armorType = armorType;
    }

    public String getAdv() {
        return adv;
    }

    public void setAdv(String adv) {
        this.adv = adv;
    }

    public String getMar() {
        return mar;
    }

    public void setMar(String mar) {
        this.mar = mar;
    }

    public List<SpecialRule> getOptionalRules() {
        return optionalRules;
    }

    public void setOptionalRules(List<SpecialRule> optionalRules) {
        this.optionalRules = optionalRules;
    }

    public List<Integer> getStompApplicableList() {
        return stompApplicableList;
    }

    public void setStompApplicableList(List<Integer> newValue) {
        stompApplicableList = newValue;
    }

    //for test environment only
    public String getArmorTypeString() {
        return armorType.toString();
    }

    public String getModelHeightString() {
        return modelHeight.toString();
    }

    public String getModelTypeString() {
        return modelType.toString();
    }

}
