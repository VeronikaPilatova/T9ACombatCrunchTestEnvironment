package cz.czechitas.webapp.Service;

import java.util.*;
import cz.czechitas.webapp.Service.Result.*;
import cz.czechitas.webapp.Service.Units.*;

import static java.lang.Math.*;

public class Combat {

    private Unit unit1;
    private Unit unit2;
    private Unit unit1Original;
    private Unit unit2Original;
    private List<Unit> units;
    List<OffensiveProfile> allProfiles;

    private int round;
    private CombatOutcome outcome;
    private int rolled6;
    private List<String> combatDescription;

    public Combat(Unit unit1, Unit unit2) {
        this.unit1 = unit1;
        this.unit1Original = unit1;
        this.unit2 = unit2;
        this.unit2Original = unit2;
        this.units = new ArrayList<>();
        this.units.add(this.unit1);
        this.units.add(this.unit2);

        this.round = 0;
        this.rolled6 = 0;
        this.combatDescription = new ArrayList<String>();
    }

    public Outcome fullCombat(int combatPhases) {
        //preparation
        round = 0;
        outcome = null;
        rolled6 = 0;
        allProfiles = sortByAgi(); //prepare a list of offensive profiles in the correct order

        for (Unit unit : units) {
            //apply general leadership
            if (unit.getGeneralLeadership() > 0) {
                int leadership = max(unit.getGeneralLeadership(), unit.getLeadership());
                unit.setLeadership(leadership);
            }
            //apply special rules
            specialRulesGlobal(unit);
        }

        //go through combat phases
        for (int i = 0; i < combatPhases; i++) {
            combatPhase();
            if (outcome != null) {
                break;
            }
        }
        if (outcome == null) {
            outcome = CombatOutcome.COMBAT_CONTINUES;
        }
        combatDescription.add("Final result is " + outcome + " in combat phase number " + round);
        Outcome fullOutcome = new Outcome(round, outcome, combatDescription);

        unit1.resetUnit();
        unit2.resetUnit();
        return fullOutcome;
    }

    private void combatPhase() {
        //preparation
        round += 1;
        unit1.setWoundsInRound(0);
        unit2.setWoundsInRound(0);
        combatDescription.add("");
        combatDescription.add("- ROUND " + round + " -");
        combatDescription.add(unit1.getName() + ": " + unit1.getModelCount() + " models, " + unit1.getAllRows() + "x" + unit1.getRowModels()
                + " fighting " + unit2.getName() + ": " + unit2.getModelCount() + " models, " + unit2.getAllRows() + "x" + unit2.getRowModels());
        //special rules
        for (Unit unit : units) {
            specialRulesRound(unit);
        }
        //check for first round and charge, apply devastating charge when needed
        if (round == 1) {
            for (Unit unit : units) {
                if (unit.getCharge() == 1) {
                    for (OffensiveProfile profile : unit.getOffensiveProfiles()) {
                        applyDevastatingCharge(profile, 1);
                    }
                }
            }
        }
        //on second round remove changes made by devastating charge
        if (round == 2) {
            for (Unit unit : units) {
                if (unit.getCharge() == 1) {
                    for (OffensiveProfile profile : unit.getOffensiveProfiles()) {
                        applyDevastatingCharge(profile, -1);
                    }
                }
            }
        }
        //roll the dice
        //impact hits
        if (round == 1) {
            for (OffensiveProfile profile : allProfiles) {
                if (profile.getImpactHits() != Dice.NONE && identifyUnit(profile).getCharge() == 1) {
                    Unit defender = identifyOpposingUnit(identifyUnit(profile));
                    combatDescription.add("Impact hits from " + profile.getName());
                    killedNoSpecialRules(profile, defender, profile.getImpactHits().toNumber());
                }
            }
            //remove killed models unless the first profile to attack is also on initiative step 10
            if (allProfiles.get(0).getAgiCurrent() != 10) {
                if (unit1.getWoundsOnAgiStep() > 0) { removeCasualties(unit1); }
                if (unit2.getWoundsOnAgiStep() > 0) { removeCasualties(unit2); }
            }
        }
        //standard combat
        for (OffensiveProfile profile : allProfiles) {
            combatDescription.add(profile.getName() + " attacking");
            killedModelsOP(profile, identifyOpposingUnit(identifyUnit(profile)));
            //grinding attacks
            if (profile.getGrindingHits() != Dice.NONE && !(round == 1 && identifyUnit(profile).getCharge() == 1 && profile.getImpactHits() != Dice.NONE)) {
                Unit defender = identifyOpposingUnit(identifyUnit(profile));
                combatDescription.add(profile.getName() + " grinding");
                killedNoSpecialRules(profile, defender, profile.getGrindingHits().toNumber());
            }
            //remove killed models if this initiative step is finished
            if (!nextProfileSimultaneous(allProfiles, profile) && !(nextProfileStomp(allProfiles, profile) && profile.getAgiCurrent() == 0)) {
                removeCasualties(unit1);
                removeCasualties(unit2);
            }
        }
        //stomp
        for (OffensiveProfile profile : allProfiles) {
            if (profile.getStomp() != Dice.NONE) {
                Unit defender = identifyOpposingUnit(identifyUnit(profile));
                if (defender.getModelHeight() == ModelHeight.STANDARD && defender.getModelType() != ModelType.CAVALRY) {
                    combatDescription.add(profile.getName() + " stomping");
                    killedNoSpecialRules(profile, defender, profile.getStomp().toNumber());
                }
            }
        }
        if (unit1.getWoundsOnAgiStep() > 0) { removeCasualties(unit1); }
        if (unit2.getWoundsOnAgiStep() > 0) { removeCasualties(unit2); }
        //if no unit is destroyed roll for break test
        if (outcome == null) {
            combatScoreAndBreakTest();
        }
    }

    private void applyDevastatingCharge(OffensiveProfile profile, int direction) {
        Unit unit = identifyUnit(profile);
        DevastatingCharge values = profile.getDevastatingCharge();
        profile.setAtt(profile.getAtt() + (values.getAtt() * direction));
        profile.setOff(profile.getOff() + (values.getOff() * direction));
        profile.setStr(profile.getStr() + (values.getStr() * direction));
        profile.setAp(profile.getAp() + (values.getAp() * direction));
        profile.setAgi(profile.getAgi() + (values.getAgi() * direction));
        unit.setDef(unit.getDef() + (values.getDef() * direction));
        unit.setRes(unit.getRes() + (values.getRes() * direction));
        unit.setArm(unit.getArm() + (values.getArm() * direction));
        unit.setFortitudeSave(unit.getFortitudeSave() + (values.getFortitudeSave() * direction));
        unit.setAegisSave(unit.getAegisSave() + (values.getAegisSave() * direction));
        unit.setLeadership(unit.getLeadership() + (values.getLeadership() * direction));
        unit.setSupportingRows(unit.getSupportingRows() + (values.getSupportingRows() * direction));
        if (direction == 1) {
            for (SpecialRule rule : values.getSpecialRulesOffensiveProfile()) {
                profile.getSpecialRules().add(rule);
            }
            for (SpecialRule rule : values.getSpecialRulesArmybookEntry()) {
                unit.getSpecialRules().add(rule);
            }
        } else {
            for (SpecialRule rule : values.getSpecialRulesOffensiveProfile()) {
                profile.getSpecialRules().remove(rule);
            }
            for (SpecialRule rule : values.getSpecialRulesArmybookEntry()) {
                unit.getSpecialRules().remove(rule);
            }
        }
    }

    private int getUnitWidth(Unit unit) {
        int width = unit.getBaseWidth() * unit.getRowModelsCurrent();
        if (unit.getSpecialRules().contains(SpecialRule.SKIRMISHER)) {
            width += 12.5 * (unit.getRowModelsCurrent() - 1);
        }
        return width;
    }

    private int getModelsInContact(Unit unit) {
        int thisUnitWidth = getUnitWidth(unit);
        int otherUnitWidth = getUnitWidth(identifyOpposingUnit(unit));
        int contactWidth = min(thisUnitWidth, otherUnitWidth);
        int modelsInContact;

        if (unit.getSpecialRules().contains(SpecialRule.SKIRMISHER)) {
            if (thisUnitWidth >= contactWidth) {
                modelsInContact = unit.getRowModelsCurrent();
            } else {
                double width = 0;
                modelsInContact = 1;
                while (width < contactWidth) {
                    modelsInContact += 1;
                    width += unit.getBaseWidth() + 12.5;
                }
                if (width == contactWidth) {
                    modelsInContact += 1;
                }
            }
        } else {
            modelsInContact = contactWidth / unit.getBaseWidth();
            if (unit.getRowModelsCurrent() - modelsInContact <= 2) {
                modelsInContact += (unit.getRowModelsCurrent() - modelsInContact);
            } else {
                modelsInContact += 2;
            }
        }
        return modelsInContact;
    }

    private List<OffensiveProfile> listAllProfiles() {
        // put all offensive profiles in combat into one list
        List<OffensiveProfile> allProfiles = new ArrayList<>();
        allProfiles.addAll(unit1.getOffensiveProfiles());
        allProfiles.addAll(unit2.getOffensiveProfiles());
        return allProfiles;
    }

    private List<OffensiveProfile> sortByAgi() {
        List<OffensiveProfile> profiles = listAllProfiles();
        //add agi bonus for charge if applicable
        if (round == 1) {
            for (OffensiveProfile profile : profiles) {
                Unit unit = identifyUnit(profile);
                profile.setAgiCurrent(profile.getAgi() + unit.getCharge());
            }
        } else {
            for (OffensiveProfile profile : profiles) {
                profile.setAgiCurrent(profile.getAgi());
            }
        }
        //sort profiles by current agility
        Collections.sort(profiles);
        Collections.reverse(profiles);
        return profiles;
    }

    private Unit identifyUnit(OffensiveProfile profile) {
        if (profile.getUnitId().equals(unit1.getUnitId())) {
            return unit1;
        } else if (profile.getUnitId().equals(unit2.getUnitId())) {
            return unit2;
        } else {
            return null;
        }
    }

    private Unit identifyOpposingUnit(Unit unit) {
        if (unit.equals(unit1)) {
            return unit2;
        } else if (unit.equals(unit2)) {
            return unit1;
        } else {
            return null;
        }
    }

    private int getSupportingAttacks(OffensiveProfile attacker) {
        int supportingRows = identifyUnit(attacker).getSupportingRows();
        Unit attackingUnit = identifyUnit(attacker);
        //speak gives fight in extra rank
        if (attacker.getActualWeapon().equals(WeaponType.SPEAR)) {
            supportingRows += 1;
        }
        //line formation gives fight in extra rank
        if (attackingUnit.getRowModelsCurrent() >= 8) {
            supportingRows += 1;
        }
        // if there are more rows behind the first than can support, calculate attacks for full supporting rows,
        // otherwise calculate support attacks for all models not in first row
        if (attackingUnit.getAllRows() - 1 > supportingRows) {
            return getModelsInContact(attackingUnit) * supportingRows * min(attacker.getAtt(), attacker.getSupportingAttacks());
        } else if (attackingUnit.getAllRows() == 1) {
            return 0;
        } else {
            return (getModelsInContact(attackingUnit) * (attackingUnit.getAllRows() - 2) + min(attackingUnit.getLastRow(), getModelsInContact(attackingUnit))) * min(attacker.getAtt(), attacker.getSupportingAttacks());
        }
    }

    private int getAllAttacks(OffensiveProfile attacker) {
        Unit attackingUnit = identifyUnit(attacker);
        int attacksPerProfile = attacker.getAtt();
        //in case of a giant with Rage
        if (attacker.getSpecialRules().contains(SpecialRule.RAGE)) {
            attacksPerProfile = attacker.getAtt() + (attackingUnit.getLostHitPoints());
        }
        int frontAttacks = getModelsInContact(attackingUnit) * attacksPerProfile + attackingUnit.getChampion() * attacker.getChampionApplicable();
        int supportingAttacks = getSupportingAttacks(attacker);
        combatDescription.add("Attacks from " + attacker.getName() + ": " + frontAttacks + " front + " + supportingAttacks + " supporting");
        return (frontAttacks + supportingAttacks) * attacker.getCountInUnit();
    }

    private int getHitDifficulty(OffensiveProfile attacker, Unit defender) {
        combatDescription.add("Rolling for hit");
        int off = attacker.getOff();
        int def = defender.getDef();
        if (defender.getSpecialRules().contains(SpecialRule.PARRY) || (defender.usingShield()) && defender.isOnFoot()) {
            def = max(attacker.getOff(), def + 1);
        }

        int diff = off - def;
        int difficulty;
        if (diff > 3) {
            difficulty = 2;
        } else if (diff > 0) {
            difficulty = 3;
        } else if (diff > -4) {
            difficulty = 4;
        } else if (diff > -8) {
            difficulty = 5;
        } else {
            difficulty = 6;
        }
        //special rules
        difficulty = difficulty + identifyUnit(attacker).getFailedFear() - defender.getFailedFear();
        if (defender.getSpecialRules().contains(SpecialRule.DISTRACTING)) {
            difficulty += 1;
        }
        if (attacker.getSpecialRules().contains(SpecialRule.LIGHTNING_REFLEXES) && attacker.getActualWeapon() != WeaponType.GREAT) {
            difficulty -= 1;
        }
        //return difficulty
        combatDescription.add("Needs to roll at least " + difficulty);
        return difficulty;
    }

    private int getWoundDifficulty(OffensiveProfile attacker, Unit defender) {
        combatDescription.add("Rolling for wound");
        int diff = attacker.getStr() - defender.getRes();
        int difficulty;
        if (diff > 1) {
            difficulty = 2;
        } else if (diff > 0) {
            difficulty = 3;
        } else if (diff > -1) {
            difficulty = 4;
        } else if (diff > -2) {
            difficulty = 5;
        } else {
            difficulty = 6;
        }
        combatDescription.add("Needs to roll at least " + difficulty);
        return difficulty;
    }

    private int getArmorDifficulty(OffensiveProfile attacker, Unit defender) {
        combatDescription.add("Rolling for armor");
        int difficulty = max((defender.getArmorSave() + attacker.getAp()), 2);
        combatDescription.add("Needs to roll at least " + difficulty);
        return difficulty;
    }

    private int getSpecialSavesDifficulty(OffensiveProfile attacker, Unit defender) {
        combatDescription.add("Rolling for special saves");
        //load original values
        int aegis = defender.getAegisSave();
        int fortitude = defender.getAegisSave();
        //special rules
        if (attacker.getSpecialRules().contains(SpecialRule.FLAMING_ATTACKS)) {
            fortitude = 11;
        }
        //return the better save
        if (aegis == 0) {
            aegis = 11;
        }
        if (fortitude == 0) {
            fortitude = 11;
        }
        int specialSave = min(aegis, fortitude);
        if (specialSave == 11) {
            combatDescription.add("No special saves");
            return 0;
        } else {
            combatDescription.add("Needs to roll at least " + specialSave);
            return specialSave;
        }
    }

    private int multiplyWounds(int wounds, OffensiveProfile attacker, Unit defender) {
        int multipliedWounds = wounds;
        if (attacker.getMultipleWounds() != Dice.NONE) {
            multipliedWounds = 0;
            for (int i = 0; i < wounds; i++) {
                multipliedWounds += min(attacker.getMultipleWounds().toNumber(), defender.getHp());
                if (attacker.getSpecialRules().contains(SpecialRule.CLIPPED_WINGS) && defender.getSpecialRules().contains(SpecialRule.FLY)) {
                    multipliedWounds += 1;
                }
            }
            combatDescription.add(wounds + " wounds multiplied to " + multipliedWounds);
        }
        return multipliedWounds;
    }

    private int rollSuccess(int times, int difficulty, boolean reroll, boolean rerollNegative) {
        int result = 0;
        rolled6 = 0;
        if (difficulty < 7) {
            for (int i = 0; i < times; i++) {
                int roll = d6();
                //ckeck if roll was a success
                if (roll >= difficulty) {
                    if (rerollNegative) {
                        int roll2 = d6();
                        if (roll2 == 6) {
                            rolled6 += 1;
                        }
                        if (roll2 >= difficulty) {
                            result += 1;
                        }
                    } else {
                        //check if rolled 6
                        if (roll == 6) {
                            rolled6 += 1;
                        }
                        result += 1;
                    }
                } else if (reroll) { //if unsuccessful and reroll is applicable
                    int roll2 = d6();
                    if (roll2 == 6) {
                        rolled6 += 1;
                    }
                    if (roll2 >= difficulty) {
                        result += 1;
                    }
                }
            }
        }
        combatDescription.add("Number of successes rolled: " + result);
        return result;
    }

    private void killedModelsOP(OffensiveProfile attacker, Unit defender) {
        int autowound = 0;
        int noArmor = 0;
        boolean rerollSpecialSavesNeg = false;

        //roll the dice
        Unit attackingUnit = identifyUnit(attacker);
        int attacks = getAllAttacks(attacker);
        //hit
        int hits = rollSuccess(attacks, getHitDifficulty(attacker, defender), attacker.isRerollHit(), false);
        if (attacker.getSpecialRules().contains(SpecialRule.BATTLE_FOCUS)) {
            hits += rolled6;
        }
        if (attacker.getSpecialRules().contains(SpecialRule.POISON)) {
            hits -= rolled6;
            autowound = rolled6;
        }

        //wound
        int wounds = rollSuccess(hits, getWoundDifficulty(attacker, defender), attacker.isRerollWound(), false) + autowound;
        if (attacker.getSpecialRules().contains(SpecialRule.LETHAL_STRIKE)) {
            wounds -= rolled6;
            noArmor = rolled6;
        }

        //armor
        int woundsAfterArmor = wounds - rollSuccess(wounds, getArmorDifficulty(attacker, defender), false, false) + noArmor;
        //special saves
        int woundsAfterSpecialSaves = woundsAfterArmor;
        if (attacker.getSpecialRules().contains(SpecialRule.DIVINE_ATTACKS) && defender.getAegisSave() > 0 && (defender.getFortitudeSave() == 0 || defender.getAegisSave() < defender.getFortitudeSave())) {
            rerollSpecialSavesNeg = true;
        }
        if (getSpecialSavesDifficulty(attacker, defender) != 0) {
            int woundsToSave = woundsAfterArmor;
            if (attacker.getSpecialRules().contains(SpecialRule.LETHAL_STRIKE)) {
                if (defender.getFortitudeSave() > 0 && (defender.getAegisSave() == 0 || defender.getFortitudeSave() < defender.getAegisSave())) {
                    woundsToSave -= noArmor;
                }
                if (attacker.getSpecialRules().contains(SpecialRule.STRENGTH_FROM_FLESH)) {
                    int lethalSaved = 0;
                    //if using Aegis
                    if (defender.getAegisSave() > 0 && (defender.getFortitudeSave() == 0 || defender.getAegisSave() < defender.getFortitudeSave())) {
                        lethalSaved = rollSuccess(noArmor, getSpecialSavesDifficulty(attacker, defender), false, rerollSpecialSavesNeg);
                        woundsAfterSpecialSaves -= lethalSaved;
                        woundsToSave -= noArmor;
                    }
                    //heal the Gortach
                    if (noArmor - lethalSaved > 0) {
                        identifyUnit(attacker).setHp(identifyUnit(attacker).getHp() + 1);
                    }
                    //multiply wounds
                    for (int i = 0; i < noArmor - lethalSaved; i++) {
                        woundsAfterSpecialSaves += min(d3(), defender.getHp()) - 1;
                    }
                }
            }
            woundsAfterSpecialSaves -= rollSuccess(woundsToSave, getSpecialSavesDifficulty(attacker, defender), false, rerollSpecialSavesNeg);
        }
        int finalWounds = multiplyWounds(woundsAfterSpecialSaves, attacker, defender);
        saveInflictedWounds(finalWounds, defender);
    }

    private void saveInflictedWounds(int wounds, Unit defender) {
        defender.setWoundsOnAgiStep(defender.getWoundsOnAgiStep() + wounds);
        defender.setWoundsInRound(defender.getWoundsInRound() + wounds);
        combatDescription.add("Inflicted " + wounds + " wounds");
    }

    private void removeCasualties(Unit defender) {
        int lostHitPoints = defender.getWoundsOnAgiStep() + defender.getLostHitPoints();
        int killedModels = lostHitPoints / defender.getHp();
        defender.setLostHitPoints(lostHitPoints % defender.getHp());
        defender.setModelCount(defender.getModelCount() - killedModels);
        combatDescription.add(killedModels + " casualties removed from unit " + defender.getName() + ", " + max(defender.getModelCount(), 0) + " models remaining");
        defender.setWoundsOnAgiStep(0);
        //is combat ended?
        if (unit1.getModelCount() <= 0 && unit2.getModelCount() <= 0) {
            outcome = CombatOutcome.MUTUAL_DESTRUCTION;
            combatDescription.add("Both units destroyed, combat ended");
        } else if (unit1.getModelCount() <= 0) {
            outcome = CombatOutcome.UNIT1_DESTROYED;
            combatDescription.add("Unit " + unit1.getName() + " destroyed, combat ended");
        } else if (unit2.getModelCount() <= 0) {
            outcome = CombatOutcome.UNIT2_DESTROYED;
            combatDescription.add("Unit " + unit2.getName() + " destroyed, combat ended");
        }
    }

    private void killedNoSpecialRules(OffensiveProfile attacker, Unit defender, int attacks) {
        Unit attackingUnit = identifyUnit(attacker);
        //hit
        int hits = rollSuccess(attacks, getHitDifficulty(attacker, defender), false, false);
        //wound
        int wounds = rollSuccess(hits, getWoundDifficulty(attacker, defender), false, false);
        //armor
        int woundsAfterArmor = wounds - rollSuccess(wounds, getArmorDifficulty(attacker, defender), false, false);
        //special saves
        int woundsAfterSpecialSaves = woundsAfterArmor;
        if (getSpecialSavesDifficulty(attacker, defender) != 0) {
            int woundsToSave = woundsAfterArmor;
            woundsAfterSpecialSaves -= rollSuccess(woundsToSave, getSpecialSavesDifficulty(attacker, defender), false, false);
        }
        int finalWounds = multiplyWounds(woundsAfterSpecialSaves, attacker, defender);
        saveInflictedWounds(finalWounds, defender);
    }

    private boolean nextProfileSimultaneous(List<OffensiveProfile> allProfiles, OffensiveProfile currentProfile) {
        OffensiveProfile nextProfile = findNextProfile(allProfiles, currentProfile);
        if (nextProfile != null) {
            if (currentProfile.getAgiCurrent() == nextProfile.getAgiCurrent()) {
                combatDescription.add("Next attack is simultaneous");
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean nextProfileStomp(List<OffensiveProfile> allProfiles, OffensiveProfile currentProfile) {
        //if there are any more profiles to go through, return false
        if (allProfiles.indexOf(currentProfile) != allProfiles.size() - 1) {
            return false;
        }
        for (OffensiveProfile profile : allProfiles) {
            if (profile.getStomp() != Dice.NONE) {
                return true;
            }
        }
        return false;
    }

    private OffensiveProfile findNextProfile(List<OffensiveProfile> allProfiles, OffensiveProfile currentProfile) {
        int index1 = allProfiles.indexOf(currentProfile);
        int index2 = index1 + 1;
        if (index2 < 0 || index2 >= allProfiles.size()) {
            return null;
        } else {
            return allProfiles.get(index2);
        }
    }

    private void combatScoreAndBreakTest() {
        int unit1Score = getCombatScore(unit1);
        combatDescription.add("Combat result for " + unit1.getName() + " is " + unit1Score);
        int unit2Score = getCombatScore(unit2);
        combatDescription.add("Combat result for " + unit2.getName() + " is " + unit2Score);
        int combatScoreDiff = abs(unit1Score - unit2Score);

        Unit loser = determineLoser(unit1Score, unit2Score);
        if (loser != null) {
            boolean breakTestPassed;

            if (loser.getSpecialRules().contains(SpecialRule.UNDEAD)) {
                //if loser is undead, remove extra models
                int wounds = combatScoreDiff;
                if (loser.getSpecialRules().contains(SpecialRule.STUBBORN)) {
                    wounds = (int) ceil(wounds / 2.0);
                }
                if (loser.getFullRanks() > identifyOpposingUnit(loser).getFullRanks()) {
                    wounds = min(wounds, 12);
                }
                if (loser.getBsb() == 1) {
                    wounds -= min(wounds, (max(min(loser.getFullRanks(), 3), 1)));
                }
                loser.setWoundsOnAgiStep(loser.getWoundsOnAgiStep() + wounds);
                removeCasualties(loser);
            } else if (loser.getSpecialRules().contains(SpecialRule.SUPERNAL)) {
                //if loser is a supernal, roll reak test and if not passed remove extra models
                if (!breakTestPassed(loser, combatScoreDiff)) {
                    int wounds = combatScoreDiff;
                    loser.setWoundsOnAgiStep(loser.getWoundsOnAgiStep() + wounds);
                    removeCasualties(loser);
                }
            } else if (loser.getSpecialRules().contains(SpecialRule.UNSTABLE)) {
                //if loser is unstable without any of the previous two remove extra models
                int wounds = combatScoreDiff;
                loser.setWoundsOnAgiStep(loser.getWoundsOnAgiStep() + wounds);
                removeCasualties(loser);
            } else {
                //else roll break test as normal
                //if unit is unbreakable skip, otherwise roll discipline
                if (loser.getSpecialRules().contains(SpecialRule.UNBREAKABLE)) {
                    breakTestPassed = true;
                } else {
                    breakTestPassed = breakTestPassed(loser, combatScoreDiff);
                }
                if (!breakTestPassed && loser.equals(unit1)) {
                    if (loser.getSpecialRules().contains(SpecialRule.WARMACHINE)) {
                        System.out.println(loser.getName() + " destroyed");
                        outcome = CombatOutcome.UNIT1_DESTROYED;

                    } else {
                        System.out.println(loser.getName() + " fled");
                        outcome = CombatOutcome.UNIT1_FLED;
                    }
                } else if (!breakTestPassed && loser.equals(unit2)) {
                    if (loser.getSpecialRules().contains(SpecialRule.WARMACHINE)) {
                        System.out.println(loser.getName() + " destroyed");
                        outcome = CombatOutcome.UNIT2_DESTROYED;

                    } else {
                        System.out.println(loser.getName() + " fled");
                        outcome = CombatOutcome.UNIT2_FLED;
                    }
                }
            }
        }
    }

    private int getCombatScore(Unit unit) {
        //get rank bonus only if not in line formation
        int rankBonus = 0;
        if (unit.getRowModelsCurrent() < 8 && !unit.getSpecialRules().contains(SpecialRule.LIGHT_TROOPS) && !unit.getSpecialRules().contains(SpecialRule.SOBER)) {
            rankBonus = min(unit.getFullRanks(), 3);
        }
        //get charge bonus in forst combat phase only
        int chargeBonus = 0;
        if (round == 1) {
            chargeBonus = unit.getCharge();
        }
        int combatScore = identifyOpposingUnit(unit).getWoundsInRound() + unit.getStandard() + chargeBonus + rankBonus;
        return combatScore;
    }

    private Unit determineLoser(int unit1Score, int unit2Score) {
        if (unit1Score > unit2Score) {
            combatDescription.add(unit2.getName() + " lost");
            return unit2;
        } else if (unit2Score > unit1Score) {
            combatDescription.add(unit1.getName() + " lost");
            return unit1;
        } else {
            combatDescription.add("There is no winner");
            return null;
        }
    }

    private boolean breakTestPassed(Unit unit, int combatScoreDiff) {
        //get roll difficulty
        int modifier = combatScoreDiff;
        if (unit.getFullRanks() > identifyOpposingUnit(unit).getFullRanks() || unit.getSpecialRules().contains(SpecialRule.STUBBORN)) {
            modifier = 0;
        }
        int difficulty = unit.getLeadership() - modifier;

        //roll and return result
        combatDescription.add("Must roll less than " + difficulty);
        int roll = d6() + d6();
        combatDescription.add("Roll result is " + roll);
        //if unit has access to BSB reroll and take better
        if (unit.getBsb() == 1) {
            int roll2 = d6() + d6();
            combatDescription.add("Second foll for BSB is " + roll2);
            roll = min(roll, roll2);
        }
        if (roll > difficulty) {
            return false;
        } else {
            combatDescription.add(unit.getName() + " passed discipline test");
            return true;
        }
    }

    //special rules
    private void specialRulesGlobal(Unit unit) {
        scoring(unit);
        auraOfMadness(unit);
        fear(unit);
        wallOfIron(unit);
        supernalMagicalAttacks(unit);
        cragWarden(unit);
        brothersOfVengeance(unit);

        for (OffensiveProfile profile : unit.getOffensiveProfiles()) {
            drunk(profile); //sets devastating charge
            sturdy(profile); //sets devastating charge
            flamingAttacks(profile);
            weaponEffects(profile);
        }
    }

    private void specialRulesRound(Unit unit) {
        rollFear(unit);
        shieldWall(unit);

        for (OffensiveProfile profile : unit.getOffensiveProfiles()) {
            //rules giving reroll for hit for this round: only when no reroll for whole combat
            if (!profile.isRerollHitBasic()) {
                profile.setRerollHit(false);
                if (profile.getSpecialRules().contains(SpecialRule.HATRED) && round == 1) {
                    profile.setRerollHit(true);
                }
                if (profile.getSpecialRules().contains(SpecialRule.PRIMAL_INSTINCT) && !profile.isRerollHit()) {
                    if (breakTestPassed(identifyUnit(profile), 0)) {
                        profile.setRerollHit(true);
                    }
                }
            }
        }
    }

    private void weaponEffects(OffensiveProfile profile) {
        greatWeapon(profile);
        giantClub(profile);
        uprootedTree(profile);
    }

    private void scoring(Unit unit) {
        if (unit.getSpecialRules().contains(SpecialRule.SCORING)) {
            unit.setScoring(1);
        }
        if (unit.getSpecialRules().contains(SpecialRule.SOBER) || unit.getSpecialRules().contains(SpecialRule.CRAG_WARDEN)) {
            unit.setScoring(0);
        }
    }

    private void rollFear(Unit unit) {
        if (unit.getSpecialRules().contains(SpecialRule.FEAR) && !identifyOpposingUnit(unit).getSpecialRules().contains(SpecialRule.FEAR) && !identifyOpposingUnit(unit).getSpecialRules().contains(SpecialRule.FEARLESS)) {
            combatDescription.add("Need to roll discipline for " + identifyOpposingUnit(unit).getName() + " (fear)");
            if (breakTestPassed(identifyOpposingUnit(unit), 0)) {
                identifyOpposingUnit(unit).setFailedFear(0);
            } else {
                identifyOpposingUnit(unit).setFailedFear(1);
            }
        }
    }

    private void fear(Unit unit) {
        if (unit.getSpecialRules().contains(SpecialRule.FEAR) && !identifyOpposingUnit(unit).getSpecialRules().contains(SpecialRule.FEAR) && !identifyOpposingUnit(unit).getSpecialRules().contains(SpecialRule.FEARLESS)) {
            identifyOpposingUnit(unit).setLeadership(unit.getLeadership() - 1);
        }
    }

    private void flamingAttacks(OffensiveProfile profile) {
        if (profile.getSpecialRules().contains(SpecialRule.FLAMING_ATTACKS) && identifyOpposingUnit(identifyUnit(profile)).getSpecialRules().contains(SpecialRule.FLAMEABLE)) {
            profile.setRerollWound(true);
        }
    }

    private void auraOfMadness(Unit unit) {
        if (unit.getSpecialRules().contains(SpecialRule.AURA_OF_MADNESS)) {
            identifyOpposingUnit(unit).setLeadership(unit.getLeadership() - 1);
        }
    }

    private void drunk(OffensiveProfile profile) {
        if (identifyUnit(profile).getSpecialRules().contains(SpecialRule.DRUNK)) {
            profile.getDevastatingCharge().setStr(profile.getDevastatingCharge().getStr() + 1);
            profile.getDevastatingCharge().setAp(profile.getDevastatingCharge().getAp() + 1);
            identifyUnit(profile).getSpecialRules().add(SpecialRule.FEARLESS);
        }
    }

    private void supernalMagicalAttacks(Unit unit) {
        if (unit.getSpecialRules().contains(SpecialRule.SUPERNAL)) {
            unit.getSpecialRules().add(SpecialRule.MAGICAL_ATTACKS);
        }
    }

    private void shieldWall(Unit unit) {
        if (!(unit.getAegisSave() != 0 && unit.getAegisSave() < 6) && unit.getSpecialRules().contains(SpecialRule.SHIELD_WALL) && unit.usingShield()) {
            unit.setAegisSave(6);
            if (identifyOpposingUnit(unit).getCharge() == 1 && round == 1) {
                unit.setAegisSave(5);
            }
        }
    }

    private void wallOfIron(Unit unit) {
        if (unit.getSpecialRules().contains(SpecialRule.WALL_OF_IRON) && (unit.getAegisSave() > 5 || unit.getAegisSave() == 0)) {
            unit.setAegisSave(5);
        }
    }

    private void sturdy(OffensiveProfile profile) {
        if (profile.getSpecialRules().contains(SpecialRule.STURDY)) {
            profile.getDevastatingCharge().setStr(profile.getDevastatingCharge().getStr() + 1);
            profile.getDevastatingCharge().setAp(profile.getDevastatingCharge().getAp() + 1);
        }
    }

    private void cragWarden(Unit unit) {
        if (unit.getSpecialRules().contains(SpecialRule.CRAG_WARDEN)) {
            unit.getSpecialRules().add(SpecialRule.SKIRMISHER);
            unit.getSpecialRules().add(SpecialRule.LIGHT_TROOPS);
            unit.getSpecialRules().add(SpecialRule.HARD_TARGET);
        }
    }

    private void brothersOfVengeance(Unit unit) {
        if (unit.getSpecialRules().contains(SpecialRule.BROTHERS_OF_VENGEANCE)) {
            unit.getSpecialRules().add(SpecialRule.SKIRMISHER);
            unit.getSpecialRules().add(SpecialRule.LIGHT_TROOPS);
            unit.getSpecialRules().add(SpecialRule.HARD_TARGET);
        }
    }

    //weapons
    private void greatWeapon(OffensiveProfile profile) {
        if (profile.getActualWeapon() == WeaponType.GREAT && !profile.getSpecialRules().contains(SpecialRule.LIGHTNING_REFLEXES)) {
            profile.setAgi(0);
        }
    }

    private void giantClub(OffensiveProfile profile) {
        if (profile.getActualWeapon() == WeaponType.GIANT_CLUB) {
            profile.setStr(profile.getStr() + 1);
            profile.setAp(profile.getAp() + 1);
        }
    }

    private void uprootedTree(OffensiveProfile profile) {
        if (profile.getActualWeapon() == WeaponType.UPROOTED_TREE) {
            profile.setStr(5);
            profile.setAp(0);
        }
    }

    //dice rolling
    private int d6() {
        Random randomGenerator = new Random();
        int d6 = randomGenerator.nextInt(6) + 1;
        combatDescription.add("rolled " + d6);
        return d6;
    }

    private int d3() {
        Random randomGenerator = new Random();
        int d3 = randomGenerator.nextInt(3) + 1;
        combatDescription.add("rolled " + d3);
        return d3;
    }

}
