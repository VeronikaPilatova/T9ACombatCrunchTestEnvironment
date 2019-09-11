package cz.czechitas.webapp.Service.Units;

import java.util.*;
import java.util.concurrent.atomic.*;

import static java.lang.Math.min;

public class Unit extends ArmybookEntry {
    private static AtomicLong idSequence = new AtomicLong(1L);

    private Long unitId;
    private ArmybookEntry armybookEntry;
    private int modelCount;
    private int originalModelCount;
    private int rowModels;
    private int charge;
    private int standAndShoot;
    private int champion;
    private int musician;
    private int standard;
    private int generalLeadership;
    private int bsb;
    private List<WeaponType> actualWeapons;
    private List<WeaponTypeShooting> actualShootingWeapons;

    private int lostHitPoints;
    private int originalLostHitPoints;
    private int woundsOnAgiStep;
    private int woundsOnAgiStepImpact;
    private int woundsInRound;
    private int scoring;
    private int failedFear;

    public Unit(ArmybookEntry armybookEntry, int modelCount, int rowModels, int champion, int musician, int standard, int generalLeadership, int bsb, int charge, int standAndShoot, List<WeaponType> actualWeapons, List<WeaponTypeShooting> actualShootingWeapons, int lostHitPoints) {
        this.unitId = idSequence.getAndIncrement();
        this.modelCount = modelCount;
        this.originalModelCount = modelCount;
        this.rowModels = rowModels;
        this.champion = champion;
        this.musician = musician;
        this.standard = standard;
        this.generalLeadership = generalLeadership;
        this.bsb = bsb;
        this.charge = charge;
        this.standAndShoot = standAndShoot;
        this.armybookEntry = armybookEntry;
        this.name = armybookEntry.getName();
        this.hp = armybookEntry.getHp();
        this.def = armybookEntry.getDef();
        this.res = armybookEntry.getRes();
        this.arm = armybookEntry.getArm();
        this.fortitudeSave = armybookEntry.getFortitudeSave();
        this.aegisSave = armybookEntry.getAegisSave();
        this.leadership = armybookEntry.getLeadership();
        this.baseWidth = armybookEntry.getBaseWidth();
        this.baseLength = armybookEntry.getBaseLength();
        this.modelHeight = armybookEntry.getModelHeight();
        this.modelType = armybookEntry.getModelType();
        this.supportingRows = armybookEntry.getSupportingRows();
        this.minModels = armybookEntry.getMinModels();
        this.maxModels = armybookEntry.getMaxModels();
        this.specialRules = armybookEntry.getSpecialRules();
        this.actualWeapons = actualWeapons;
        this.actualShootingWeapons = actualShootingWeapons;
        this.lostHitPoints = lostHitPoints;
        this.originalLostHitPoints = lostHitPoints;

        this.woundsOnAgiStep = 0;
        this.woundsInRound = 0;
        this.failedFear = 0;

        //create offensive profiles including weapons
        List<OffensiveProfile> offensiveTemplates = armybookEntry.getOffensiveProfiles();
        this.offensiveProfiles = new ArrayList<>();
        //create new offensive profile for this specific unit and set unit id
        for (OffensiveProfile offensiveTemplate : offensiveTemplates) {
            OffensiveProfile offensiveProfile = new OffensiveProfile(
                    offensiveTemplate.getName(),
                    offensiveTemplate.getAtt(),
                    offensiveTemplate.getOff(),
                    offensiveTemplate.getStr(),
                    offensiveTemplate.getAp(),
                    offensiveTemplate.getAgi(),
                    offensiveTemplate.getSupportingAttacks(),
                    offensiveTemplate.getMultipleWounds(),
                    offensiveTemplate.getImpactHits(),
                    offensiveTemplate.getStomp(),
                    offensiveTemplate.getGrindingHits(),
                    offensiveTemplate.getSpecialRules(),
                    offensiveTemplate.getDevastatingCharge());
            offensiveProfile.setUnitId(unitId);
            int index = offensiveTemplates.indexOf(offensiveTemplate);
            //match and set how many times the offensive profile repeats in the armybook entry
            List<Integer> countList = armybookEntry.getOffensiveProfileRepeat();
            offensiveProfile.setCountInUnit(countList.get(index).intValue());
            //match and set if champion is applicable for this profile
            List<Integer> championList = armybookEntry.getChampionApplicableList();
            offensiveProfile.setChampionApplicable(championList.get(index).intValue());
            //set actual weapon
            offensiveProfile.setActualWeapon(actualWeapons.get(index));
            offensiveProfile.setActualShootingWeapon(actualShootingWeapons.get(index));
            //add to a newly made offensiveProfiles list with specific profiles
            this.offensiveProfiles.add(offensiveProfile);
        }
    }

    public boolean usingShield() {
        for (OffensiveProfile profile : offensiveProfiles) {
            if (profile.getActualWeapon() == WeaponType.HW_SHIELD) {
                return true;
            }
        }
        return false;
    }

    public void resetUnit() {
        modelCount = originalModelCount;
        leadership = armybookEntry.getLeadership();
        lostHitPoints = originalLostHitPoints;
        woundsOnAgiStep = 0;
        woundsInRound = 0;
        failedFear = 0;
        for (OffensiveProfile profile : offensiveProfiles) {
            profile.setRerollHit(false);
            profile.setRerollWound(false);
        }
    }

    public int getModelCount() {
        return modelCount;
    }

    public void setModelCount(int newValue) {
        modelCount = newValue;
    }

    public int getRowModels() {
        return rowModels;
    }

    public void setRowModels(int newValue) {
        rowModels = newValue;
    }

    public int getRowModelsCurrent() {
        return min(modelCount, rowModels);
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long newValue) {
        unitId = newValue;
    }

    public int getAllRows() {
        return (int) Math.ceil((double) modelCount / (double) rowModels);
    }

    public int getFullRows() {
        return modelCount / rowModels;
    }

    public int getFullRanks() {
        if (getRowModels() < armybookEntry.getMinimumRankModels()) {
            return 0;
        } else if (getLastRow() >= armybookEntry.getMinimumRankModels()) {
            return getAllRows() - 1;
        } else {
            return getFullRows() - 1;
        }
    }

    public int getLastRow() {
        if (modelCount % rowModels == 0) {
            return rowModels;
        } else {
            return modelCount % rowModels;
        }
    }

    public int getLostHitPoints() {
        return lostHitPoints;
    }

    public void setLostHitPoints(int newValue) {
        lostHitPoints = newValue;
    }

    public int getWoundsOnAgiStep() {
        return woundsOnAgiStep;
    }

    public void setWoundsOnAgiStep(int newValue) {
        woundsOnAgiStep = newValue;
    }

    public int getWoundsInRound() {
        return woundsInRound;
    }

    public void setWoundsInRound(int newValue) {
        woundsInRound = newValue;
    }

    public int getCharge() {
        if (specialRules.contains(SpecialRule.WARMACHINE)) {
            return 0;
        } else {
            return charge;
        }
    }

    public int getStandAndShoot() {
        if (specialRules.contains(SpecialRule.RELOAD)) {
            return 0;
        } else {
            return standAndShoot;
        }
    }

    public void setStandAndShoot(int standAndShoot) { this.standAndShoot = standAndShoot; }

    public void setCharge(int newValue) {
        charge = newValue;
    }

    public int getChampion() {
        return champion;
    }

    public void setChampion(int newValue) {
        champion = newValue;
    }

    public int getMusician() {
        return musician;
    }

    public void setMusician(int newValue) {
        musician = newValue;
    }

    public int getStandard() {
        return standard;
    }

    public void setStandard(int newValue) {
        standard = newValue;
    }

    public ArmybookEntry getArmybookEntry() {
        return armybookEntry;
    }

    public void setArmybookEntry(ArmybookEntry newValue) {
        armybookEntry = newValue;
    }

    public int getGeneralLeadership() {
        return generalLeadership;
    }

    public void setGeneralLeadership(int newValue) {
        generalLeadership = newValue;
    }

    public int getBsb() {
        return bsb;
    }

    public void setBsb(int newValue) {
        bsb = newValue;
    }

    public List<WeaponType> getActualWeapons() { return actualWeapons; }

    public void setActualWeapons(List<WeaponType> actualWeapons) { this.actualWeapons = actualWeapons; }

    public List<WeaponTypeShooting> getActualShootingWeapons() {
        return actualShootingWeapons;
    }

    public void setActualShootingWeapons(List<WeaponTypeShooting> actualShootingWeapons) { this.actualShootingWeapons = actualShootingWeapons; }

    public int getOriginalModelCount() { return originalModelCount; }

    public void setOriginalModelCount(int originalModelCount) { this.originalModelCount = originalModelCount; }

    public int getScoring() { return scoring; }

    public void setScoring(int scoring) { this.scoring = scoring; }

    public int getFailedFear() { return failedFear; }

    public void setFailedFear(int failedFear) { this.failedFear = failedFear; }

    public int getOriginalLostHitPoints() { return originalLostHitPoints; }

    public void setOriginalLostHitPoints(int originalLostHitPoints) { this.originalLostHitPoints = originalLostHitPoints; }

    public int getWoundsOnAgiStepImpact() { return woundsOnAgiStepImpact; }

    public void setWoundsOnAgiStepImpact(int newValue) { woundsOnAgiStepImpact = newValue; }

    public int getHp() {
        if (modelCount == 1 && specialRules.contains(SpecialRule.WIZARD_CONCLAVE) && champion == 1) {
            return hp + 1;
        } else {
            return hp;
        }
    }

    //for testing environment only

    public String getChargeString() {
        if (charge == 1) {
            return "Yes";
        } else {
            return "No";
        }
    }

}
