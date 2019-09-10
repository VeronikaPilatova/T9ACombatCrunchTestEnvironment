package cz.czechitas.webapp.Service.Units;

import java.util.*;

public class OffensiveProfile implements Comparable<OffensiveProfile> {
    private String name;
    private Long unitId;
    private int att; //number of attacks
    private int off; //offensive skill
    private int str; //strength
    private int ap; //armor piercing
    private int agi; //agility/initiative
    private int supportingAttacks;
    private Dice multipleWounds;
    private Dice impactHits;
    private Dice stomp;
    private Dice grindingHits;
    private int originalAtt;
    private int originalOff;
    private int originalStr;
    private int originalAp;
    private int originalAgi;
    private List specialRules;
    private DevastatingCharge devastatingCharge;

    private int countInUnit;
    private int championApplicable;
    private WeaponType actualWeapon;
    private WeaponTypeShooting actualShootingWeapon;
    private int agiCurrent;
    private boolean rerollHit;
    private boolean rerollHitBasic;
    private boolean rerollWound;

    //for testing environment only
    List<String> specialRulesStringList;

    public OffensiveProfile(String name, int att, int off, int str, int ap, int agi, int supportingAttacks, Dice multipleWounds, Dice impactHits, Dice stomp, Dice grindingHits, List specialRules, DevastatingCharge devastatingCharge) {
        this.name = name;
        this.att = att;
        this.originalAtt = att;
        this.off = off;
        this.originalOff = off;
        this.str = str;
        this.originalStr = str;
        this.ap = ap;
        this.originalAp = ap;
        this.agi = agi;
        this.originalAgi = agi;
        this.supportingAttacks = supportingAttacks;
        this.multipleWounds = multipleWounds;
        this.impactHits = impactHits;
        this.stomp = stomp;
        this.grindingHits = grindingHits;
        this.specialRules = specialRules;
        this.devastatingCharge = devastatingCharge;
        this.rerollHit = false;
        this.rerollHitBasic = false;
        this.rerollWound = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String newValue) {
        name = newValue;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long newValue) {
        unitId = newValue;
    }

    public int getAtt() {
        if (specialRules.contains(SpecialRule.BRIAR_BEAST)) {
            Random randomGenerator = new Random();
            return randomGenerator.nextInt(6) + 2;
        } else {
            return att;
        }
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
        if (specialRules.contains(SpecialRule.TOXIC_ATTACKS)) {
            return 3;
        } else {
            return str;
        }
    }

    public void setStr(int newValue) {
        str = newValue;
    }

    public int getAp() {
        if (specialRules.contains(SpecialRule.TOXIC_ATTACKS)) {
            return 10;
        } else {
            return ap;
        }
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

    public int getSupportingAttacks() {
        return supportingAttacks;
    }

    public void setSupportingAttacks(int newValue) {
        supportingAttacks = newValue;
    }

    public WeaponType getActualWeapon() {
        return actualWeapon;
    }

    public void setActualWeapon(WeaponType newValue) {
        actualWeapon = newValue;
    }

    public WeaponTypeShooting getActualShootingWeapon() {
        return actualShootingWeapon;
    }

    public void setActualShootingWeapon(WeaponTypeShooting newValue) { actualShootingWeapon = newValue; }

    public int getCountInUnit() {
        return countInUnit;
    }

    public void setCountInUnit(int newValue) {
        countInUnit = newValue;
    }

    public int getAgiCurrent() {
        return agiCurrent;
    }

    public void setAgiCurrent(int newValue) {
        agiCurrent = newValue;
    }

    public List getSpecialRules() {
        return specialRules;
    }

    public void setSpecialRules(List newValue) {
        specialRules = newValue;
    }

    public int getChampionApplicable() { return championApplicable; }

    public void setChampionApplicable(int championApplicable) { this.championApplicable = championApplicable; }

    public boolean isRerollHit() { return rerollHit; }

    public void setRerollHit(boolean rerollHit) { this.rerollHit = rerollHit; }

    public boolean isRerollWound() { return rerollWound; }

    public void setRerollWound(boolean rerollWound) { this.rerollWound = rerollWound; }

    public int getOriginalAtt() { return originalAtt; }

    public void setOriginalAtt(int originalAtt) { this.originalAtt = originalAtt; }

    public int getOriginalOff() { return originalOff; }

    public void setOriginalOff(int originalOff) { this.originalOff = originalOff; }

    public int getOriginalStr() { return originalStr; }

    public void setOriginalStr(int originalStr) { this.originalStr = originalStr; }

    public int getOriginalAp() { return originalAp; }

    public void setOriginalAp(int originalAp) { this.originalAp = originalAp; }

    public int getOriginalAgi() { return originalAgi; }

    public void setOriginalAgi(int originalAgi) { this.originalAgi = originalAgi; }

    public boolean isRerollHitBasic() {
        return rerollHitBasic;
    }

    public void setRerollHitBasic(boolean rerollHitBasic) {
        this.rerollHitBasic = rerollHitBasic;
    }

    public Dice getMultipleWounds() {
        return multipleWounds;
    }

    public void setMultipleWounds(Dice multipleWounds) {
        this.multipleWounds = multipleWounds;
    }

    public Dice getImpactHits() {
        return impactHits;
    }

    public void setImpactHits(Dice impactHits) {
        this.impactHits = impactHits;
    }

    public Dice getStomp() {
        return stomp;
    }

    public void setStomp(Dice stomp) {
        this.stomp = stomp;
    }

    public Dice getGrindingHits() {
        return grindingHits;
    }

    public void setGrindingHits(Dice grindingHits) {
        this.grindingHits = grindingHits;
    }

    //For testing environment only
    public String getMultipleWoundsString() {
        return multipleWounds.toString();
    }

    public String getImpactHitsString() {
        return impactHits.toString();
    }

    public String getStompString() {
        return stomp.toString();
    }

    public String getGrindingHitsString() {
        return grindingHits.toString();
    }

    public String getActualWeaponString() {
        return actualWeapon.toString();
    }

    //for testing environment only
    public List<String> getSpecialRulesStringList() {
        return specialRulesStringList;
    }

    public void setSpecialRulesStringList(List<String> newValue) {
        specialRulesStringList = newValue;
    }

    public String getSpecialRulesString() {
        return String.join(", ", specialRulesStringList);
    }

    public DevastatingCharge getDevastatingCharge() {
        return devastatingCharge;
    }

    public void setDevastatingCharge(DevastatingCharge newValue) {
        devastatingCharge = newValue;
    }

    @Override
    public int compareTo(OffensiveProfile otherProfile) {
        return (this.getAgiCurrent() - otherProfile.getAgiCurrent());
    }

}
