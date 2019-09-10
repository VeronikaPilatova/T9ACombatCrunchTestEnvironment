package cz.czechitas.webapp;

import java.util.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import com.sun.xml.internal.bind.v2.*;
import cz.czechitas.webapp.Controller.Form.*;
import cz.czechitas.webapp.Service.*;
import cz.czechitas.webapp.Service.Result.*;
import cz.czechitas.webapp.Service.Units.*;

@Controller
public class HlavniController {

    private CombatCrunchService combatCrunchService;

    private List<String> specialRulesArmybookEntry = Arrays.asList(
            SpecialRule.STUBBORN.toString(),
            SpecialRule.UNBREAKABLE.toString(),
            SpecialRule.SUPERNAL.toString(),
            SpecialRule.UNDEAD.toString(),
            SpecialRule.UNSTABLE.toString(),
            SpecialRule.AURA_OF_MADNESS.toString(),
            SpecialRule.FEARLESS.toString(),
            SpecialRule.FEAR.toString(),
            SpecialRule.SKIRMISHER.toString(),
            SpecialRule.DISTRACTING.toString(),
            SpecialRule.LIGHT_TROOPS.toString(),
            SpecialRule.FLAMEABLE.toString(),
            SpecialRule.WIZARD_CONCLAVE.toString(),
            SpecialRule.PARRY.toString());

    private List<String> specialRulesOffensiveProfile = Arrays.asList(
            SpecialRule.TOXIC_ATTACKS.toString(),
            SpecialRule.BATTLE_FOCUS.toString(),
            SpecialRule.LIGHTNING_REFLEXES.toString(),
            SpecialRule.POISON.toString(),
            SpecialRule.DIVINE_ATTACKS.toString(),
            SpecialRule.RAGE.toString(),
            SpecialRule.HATRED.toString(),
            SpecialRule.PRIMAL_INSTINCT.toString(),
            SpecialRule.STRENGTH_FROM_FLESH.toString(),
            SpecialRule.FLAMING_ATTACKS.toString(),
            SpecialRule.LETHAL_STRIKE.toString());

    @RequestMapping("/")
    public ModelAndView showInputForm() {
        ModelAndView templateData = new ModelAndView("index");
        templateData.addObject("specialRulesArmybookEntry", specialRulesArmybookEntry);
        templateData.addObject("specialRulesOffensiveProfile", specialRulesOffensiveProfile);
        return templateData;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView showCombatResult(TestUnitsForm input) {
        ModelAndView templateData = new ModelAndView("result");

        //take input and create the two units
        //offensive profiles
        //preparation for unit 1 profiles
        List<OffensiveProfile> unit1Profiles = new ArrayList<>();
        List<Integer> unit1ProfilesRepeated = new ArrayList<>();
        List<Integer> unit1ProfilesChampion = new ArrayList<>();
        List<Integer> unit1ProfilesStomp = new ArrayList<>();
        List<List<WeaponType>> unit1ProfilesWeapon = new ArrayList<>();
        List<List<WeaponTypeShooting>> unit1ProfilesWeaponShooting = new ArrayList<>();
        List<WeaponType> unit1ProfilesWeaponActual = new ArrayList<>();
        List<WeaponTypeShooting> unit1ProfilesWeaponShootingActual = new ArrayList<>();
        //profile 1 or unit 1
        List<SpecialRule> op11SpecialRules = new ArrayList<>();
        for (String string : input.getOp11_specialRules()) {
            SpecialRule specialRule = SpecialRule.fromString(string);
            op11SpecialRules.add(specialRule);
        }
        OffensiveProfile op11 = new OffensiveProfile("OP1.1", input.getOp11_attacks(), input.getOp11_off(), input.getOp11_str(), input.getOp11_ap(), input.getOp11_agi(),
                1, input.getOp11_multipleWounds(), input.getOp11_impactHits(), input.getOp11_stomp(), input.getOp11_grindingHits(), op11SpecialRules, new DevastatingCharge());
        op11.setCountInUnit(input.getOp11_repeated());
        op11.setActualWeapon(input.getOp11_weapon());
        op11.setSpecialRulesStringList(input.getOp11_specialRules());
        unit1Profiles.add(op11);
        unit1ProfilesRepeated.add(input.getOp11_repeated());
        unit1ProfilesChampion.add(1);
        unit1ProfilesStomp.add(1);
        unit1ProfilesWeapon.add(Arrays.asList(input.getOp11_weapon()));
        unit1ProfilesWeaponShooting.add(Arrays.asList(WeaponTypeShooting.NONE));
        unit1ProfilesWeaponActual.add(input.getOp11_weapon());
        unit1ProfilesWeaponShootingActual.add(WeaponTypeShooting.NONE);
        //profile 2 of unit 1
        if (input.getOp12_active()) {
            List<SpecialRule> op12SpecialRules = new ArrayList<>();
            for (String string : input.getOp12_specialRules()) {
                SpecialRule specialRule = SpecialRule.fromString(string);
                op12SpecialRules.add(specialRule);
            }
            OffensiveProfile op12 = new OffensiveProfile("OP1.2", input.getOp12_attacks(), input.getOp12_off(), input.getOp12_str(), input.getOp12_ap(), input.getOp12_agi(),
                    1, input.getOp12_multipleWounds(), input.getOp12_impactHits(), input.getOp12_stomp(), input.getOp12_grindingHits(), op12SpecialRules, new DevastatingCharge());
            op12.setCountInUnit(input.getOp12_repeated());
            op12.setActualWeapon(input.getOp12_weapon());
            op12.setSpecialRulesStringList(input.getOp12_specialRules());
            unit1Profiles.add(op12);
            unit1ProfilesRepeated.add(input.getOp12_repeated());
            unit1ProfilesChampion.add(1);
            unit1ProfilesStomp.add(1);
            unit1ProfilesWeapon.add(Arrays.asList(input.getOp12_weapon()));
            unit1ProfilesWeaponShooting.add(Arrays.asList(WeaponTypeShooting.NONE));
            unit1ProfilesWeaponActual.add(input.getOp12_weapon());
            unit1ProfilesWeaponShootingActual.add(WeaponTypeShooting.NONE);
        }
        //profile 3 of unit 1
        if (input.getOp13_active()) {
            List<SpecialRule> op13SpecialRules = new ArrayList<>();
            for (String string : input.getOp13_specialRules()) {
                SpecialRule specialRule = SpecialRule.fromString(string);
                op13SpecialRules.add(specialRule);
            }
            OffensiveProfile op13 = new OffensiveProfile("OP1.3", input.getOp13_attacks(), input.getOp13_off(), input.getOp13_str(), input.getOp13_ap(), input.getOp13_agi(),
                    1, input.getOp13_multipleWounds(), input.getOp13_impactHits(), input.getOp13_stomp(), input.getOp13_grindingHits(), op13SpecialRules, new DevastatingCharge());
            op13.setCountInUnit(input.getOp13_repeated());
            op13.setActualWeapon(input.getOp13_weapon());
            op13.setSpecialRulesStringList(input.getOp13_specialRules());
            unit1Profiles.add(op13);
            unit1ProfilesRepeated.add(input.getOp13_repeated());
            unit1ProfilesChampion.add(1);
            unit1ProfilesStomp.add(1);
            unit1ProfilesWeapon.add(Arrays.asList(input.getOp13_weapon()));
            unit1ProfilesWeaponShooting.add(Arrays.asList(WeaponTypeShooting.NONE));
            unit1ProfilesWeaponActual.add(input.getOp13_weapon());
            unit1ProfilesWeaponShootingActual.add(WeaponTypeShooting.NONE);
        }
        //profile 4 of unit 1
        if (input.getOp14_active()) {
            List<SpecialRule> op14SpecialRules = new ArrayList<>();
            for (String string : input.getOp14_specialRules()) {
                SpecialRule specialRule = SpecialRule.fromString(string);
                op14SpecialRules.add(specialRule);
            }
            OffensiveProfile op14 = new OffensiveProfile("OP1.4", input.getOp14_attacks(), input.getOp14_off(), input.getOp14_str(), input.getOp14_ap(), input.getOp14_agi(),
                    1, input.getOp14_multipleWounds(), input.getOp14_impactHits(), input.getOp14_stomp(), input.getOp14_grindingHits(), op14SpecialRules, new DevastatingCharge());
            op14.setCountInUnit(input.getOp14_repeated());
            op14.setActualWeapon(input.getOp14_weapon());
            op14.setSpecialRulesStringList(input.getOp14_specialRules());
            unit1Profiles.add(op14);
            unit1ProfilesRepeated.add(input.getOp14_repeated());
            unit1ProfilesChampion.add(1);
            unit1ProfilesStomp.add(1);
            unit1ProfilesWeapon.add(Arrays.asList(input.getOp14_weapon()));
            unit1ProfilesWeaponShooting.add(Arrays.asList(WeaponTypeShooting.NONE));
            unit1ProfilesWeaponActual.add(input.getOp14_weapon());
            unit1ProfilesWeaponShootingActual.add(WeaponTypeShooting.NONE);
        }
        //profile 5 of unit1
        if (input.getOp15_active()) {
            List<SpecialRule> op15SpecialRules = new ArrayList<>();
            for (String string : input.getOp15_specialRules()) {
                SpecialRule specialRule = SpecialRule.fromString(string);
                op15SpecialRules.add(specialRule);
            }
            OffensiveProfile op15 = new OffensiveProfile("OP1.5", input.getOp15_attacks(), input.getOp15_off(), input.getOp15_str(), input.getOp15_ap(), input.getOp15_agi(),
                    1, input.getOp15_multipleWounds(), input.getOp15_impactHits(), input.getOp15_stomp(), input.getOp15_grindingHits(), op15SpecialRules, new DevastatingCharge());
            op15.setCountInUnit(input.getOp15_repeated());
            op15.setActualWeapon(input.getOp15_weapon());
            op15.setSpecialRulesStringList(input.getOp15_specialRules());
            unit1Profiles.add(op15);
            unit1ProfilesRepeated.add(input.getOp15_repeated());
            unit1ProfilesChampion.add(1);
            unit1ProfilesStomp.add(1);
            unit1ProfilesWeapon.add(Arrays.asList(input.getOp15_weapon()));
            unit1ProfilesWeaponShooting.add(Arrays.asList(WeaponTypeShooting.NONE));
            unit1ProfilesWeaponActual.add(input.getOp15_weapon());
            unit1ProfilesWeaponShootingActual.add(WeaponTypeShooting.NONE);
        }
        //preparation for unit 2 profiles
        List<OffensiveProfile> unit2Profiles = new ArrayList<>();
        List<Integer> unit2ProfilesRepeated = new ArrayList<>();
        List<Integer> unit2ProfilesChampion = new ArrayList<>();
        List<Integer> unit2ProfilesStomp = new ArrayList<>();
        List<List<WeaponType>> unit2ProfilesWeapon = new ArrayList<>();
        List<List<WeaponTypeShooting>> unit2ProfilesWeaponShooting = new ArrayList<>();
        List<WeaponType> unit2ProfilesWeaponActual = new ArrayList<>();
        List<WeaponTypeShooting> unit2ProfilesWeaponShootingActual = new ArrayList<>();
        //profile 1 of unit 2
        List<SpecialRule> op21SpecialRules = new ArrayList<>();
        for (String string : input.getOp21_specialRules()) {
            SpecialRule specialRule = SpecialRule.fromString(string);
            op21SpecialRules.add(specialRule);
        }
        OffensiveProfile op21 = new OffensiveProfile("OP2.1", input.getOp21_attacks(), input.getOp21_off(), input.getOp21_str(), input.getOp21_ap(), input.getOp21_agi(),
                1, input.getOp21_multipleWounds(), input.getOp21_impactHits(), input.getOp21_stomp(), input.getOp21_grindingHits(), op21SpecialRules, new DevastatingCharge());
        op21.setCountInUnit(input.getOp21_repeated());
        op21.setActualWeapon(input.getOp21_weapon());
        op21.setSpecialRulesStringList(input.getOp21_specialRules());
        unit2Profiles.add(op21);
        unit2ProfilesRepeated.add(input.getOp21_repeated());
        unit2ProfilesChampion.add(1);
        unit2ProfilesStomp.add(1);
        unit2ProfilesWeapon.add(Arrays.asList(input.getOp21_weapon()));
        unit2ProfilesWeaponShooting.add(Arrays.asList(WeaponTypeShooting.NONE));
        unit2ProfilesWeaponActual.add(input.getOp21_weapon());
        unit2ProfilesWeaponShootingActual.add(WeaponTypeShooting.NONE);
        //profile 2 of unit 2
        if (input.getOp22_active()) {
            List<SpecialRule> op22SpecialRules = new ArrayList<>();
            for (String string : input.getOp22_specialRules()) {
                SpecialRule specialRule = SpecialRule.fromString(string);
                op22SpecialRules.add(specialRule);
            }
            OffensiveProfile op22 = new OffensiveProfile("OP2.2", input.getOp22_attacks(), input.getOp22_off(), input.getOp22_str(), input.getOp22_ap(), input.getOp22_agi(),
                    1, input.getOp22_multipleWounds(), input.getOp22_impactHits(), input.getOp22_stomp(), input.getOp22_grindingHits(), op22SpecialRules, new DevastatingCharge());
            op22.setCountInUnit(input.getOp22_repeated());
            op22.setActualWeapon(input.getOp22_weapon());
            op22.setSpecialRulesStringList(input.getOp22_specialRules());
            unit2Profiles.add(op22);
            unit2ProfilesRepeated.add(input.getOp22_repeated());
            unit2ProfilesChampion.add(1);
            unit2ProfilesStomp.add(1);
            unit2ProfilesWeapon.add(Arrays.asList(input.getOp22_weapon()));
            unit2ProfilesWeaponShooting.add(Arrays.asList(WeaponTypeShooting.NONE));
            unit2ProfilesWeaponActual.add(input.getOp22_weapon());
            unit2ProfilesWeaponShootingActual.add(WeaponTypeShooting.NONE);
        }
        //profile 3 of unit 2
        if (input.getOp23_active()) {
            List<SpecialRule> op23SpecialRules = new ArrayList<>();
            for (String string : input.getOp23_specialRules()) {
                SpecialRule specialRule = SpecialRule.fromString(string);
                op23SpecialRules.add(specialRule);
            }
            OffensiveProfile op23 = new OffensiveProfile("OP2.3", input.getOp23_attacks(), input.getOp23_off(), input.getOp23_str(), input.getOp23_ap(), input.getOp23_agi(),
                    1, input.getOp23_multipleWounds(), input.getOp23_impactHits(), input.getOp23_stomp(), input.getOp23_grindingHits(), op23SpecialRules, new DevastatingCharge());
            op23.setCountInUnit(input.getOp23_repeated());
            op23.setActualWeapon(input.getOp23_weapon());
            op23.setSpecialRulesStringList(input.getOp23_specialRules());
            unit2Profiles.add(op23);
            unit2ProfilesRepeated.add(input.getOp23_repeated());
            unit2ProfilesChampion.add(1);
            unit2ProfilesStomp.add(1);
            unit2ProfilesWeapon.add(Arrays.asList(input.getOp23_weapon()));
            unit2ProfilesWeaponShooting.add(Arrays.asList(WeaponTypeShooting.NONE));
            unit2ProfilesWeaponActual.add(input.getOp23_weapon());
            unit2ProfilesWeaponShootingActual.add(WeaponTypeShooting.NONE);
        }
        //profile 4 of unit 2
        if (input.getOp24_active()) {
            List<SpecialRule> op24SpecialRules = new ArrayList<>();
            for (String string : input.getOp24_specialRules()) {
                SpecialRule specialRule = SpecialRule.fromString(string);
                op24SpecialRules.add(specialRule);
            }
            OffensiveProfile op24 = new OffensiveProfile("OP2.4", input.getOp24_attacks(), input.getOp24_off(), input.getOp24_str(), input.getOp24_ap(), input.getOp24_agi(),
                    1, input.getOp24_multipleWounds(), input.getOp24_impactHits(), input.getOp24_stomp(), input.getOp24_grindingHits(), op24SpecialRules, new DevastatingCharge());
            op24.setCountInUnit(input.getOp24_repeated());
            op24.setActualWeapon(input.getOp24_weapon());
            op24.setSpecialRulesStringList(input.getOp24_specialRules());
            unit2Profiles.add(op24);
            unit2ProfilesRepeated.add(input.getOp24_repeated());
            unit2ProfilesChampion.add(1);
            unit2ProfilesStomp.add(1);
            unit2ProfilesWeapon.add(Arrays.asList(input.getOp24_weapon()));
            unit2ProfilesWeaponShooting.add(Arrays.asList(WeaponTypeShooting.NONE));
            unit2ProfilesWeaponActual.add(input.getOp24_weapon());
            unit2ProfilesWeaponShootingActual.add(WeaponTypeShooting.NONE);
        }
        //profile 5 of unit 2
        if (input.getOp25_active()) {
            List<SpecialRule> op25SpecialRules = new ArrayList<>();
            for (String string : input.getOp25_specialRules()) {
                SpecialRule specialRule = SpecialRule.fromString(string);
                op25SpecialRules.add(specialRule);
            }
            OffensiveProfile op25 = new OffensiveProfile("OP2.5", input.getOp25_attacks(), input.getOp25_off(), input.getOp25_str(), input.getOp25_ap(), input.getOp25_agi(),
                    1, input.getOp25_multipleWounds(), input.getOp25_impactHits(), input.getOp25_stomp(), input.getOp25_grindingHits(), op25SpecialRules, new DevastatingCharge());
            op25.setCountInUnit(input.getOp25_repeated());
            op25.setActualWeapon(input.getOp25_weapon());
            op25.setSpecialRulesStringList(input.getOp25_specialRules());
            unit2Profiles.add(op25);
            unit2ProfilesRepeated.add(input.getOp25_repeated());
            unit2ProfilesChampion.add(1);
            unit2ProfilesStomp.add(1);
            unit2ProfilesWeapon.add(Arrays.asList(input.getOp25_weapon()));
            unit2ProfilesWeaponShooting.add(Arrays.asList(WeaponTypeShooting.NONE));
            unit2ProfilesWeaponActual.add(input.getOp25_weapon());
            unit2ProfilesWeaponShootingActual.add(WeaponTypeShooting.NONE);
        }
        //armybook entries
        List<SpecialRule> unit1SpecialRules = new ArrayList<>();
        List<SpecialRule> unit2SpecialRules = new ArrayList<>();
        for (String string : input.getU1_specialRules()) {
            SpecialRule specialRule = SpecialRule.fromString(string);
            unit1SpecialRules.add(specialRule);
        }
        for (String string : input.getU2_specialRules()) {
            SpecialRule specialRule = SpecialRule.fromString(string);
            unit2SpecialRules.add(specialRule);
        }
        ArmybookEntry ae1 = new ArmybookEntry("unit1", input.getU1_hp(), input.getU1_def(), input.getU1_res(), input.getU1_arm(),
                input.getU1_armorType(), input.getU1_fortitude(), input.getU1_aegis(), "adv", "mar", input.getU1_discipline(),
                input.getU1_baseWidth(), input.getU1_baseLength(), input.getU1_modelHeight(), input.getU1_modelType(),
                true, 1, 1, 100, 1, 1, 1,
                unit1Profiles, unit1ProfilesRepeated, unit1ProfilesChampion, unit1ProfilesStomp, unit1ProfilesWeapon, unit1ProfilesWeaponShooting,
                Armybook.TEST, unit1SpecialRules, Arrays.asList());
        ArmybookEntry ae2 = new ArmybookEntry("unit2", input.getU2_hp(), input.getU2_def(), input.getU2_res(), input.getU2_arm(),
                input.getU2_armorType(), input.getU2_fortitude(), input.getU2_aegis(), "adv", "mar", input.getU2_discipline(),
                input.getU2_baseWidth(), input.getU2_baseLength(), input.getU2_modelHeight(), input.getU2_modelType(),
                true, 1, 1, 100, 1, 1, 1,
                unit2Profiles, unit2ProfilesRepeated, unit2ProfilesChampion, unit2ProfilesStomp, unit2ProfilesWeapon, unit2ProfilesWeaponShooting,
                Armybook.TEST, unit2SpecialRules, Arrays.asList());
        //units
        Unit unit1 = new Unit(ae1, input.getU1_modelCount(), input.getU1_rowModels(), input.getU1_champion(), input.getU1_musician(), input.getU1_standard(), input.getU1_general()*input.getU1_generalDis(), input.getU1_BSB(), input.getU1_charge(), 0, unit1ProfilesWeaponActual, unit1ProfilesWeaponShootingActual, 0);
        Unit unit2 = new Unit(ae2, input.getU2_modelCount(), input.getU2_rowModels(), input.getU2_champion(), input.getU2_musician(), input.getU2_standard(), input.getU2_general()*input.getU2_generalDis(), input.getU2_BSB(), input.getU2_charge(), 0, unit2ProfilesWeaponActual, unit2ProfilesWeaponShootingActual, 0);

        //Create and run combat
        Combat combat = new Combat(unit1, unit2);
        Outcome outcome = combat.fullCombat(12);
        List<String> combatDescription = outcome.getCombatDescription();
        
        String unit1SpecialRulesString = String.join(", ", input.getU1_specialRules());
        String unit2SpecialRulesString = String.join(", ", input.getU2_specialRules());

        templateData.addObject("unit1Profiles", unit1Profiles);
        templateData.addObject("unit2Profiles", unit2Profiles);
        templateData.addObject("unit1Entry", ae1);
        templateData.addObject("unit2Entry", ae2);
        templateData.addObject("unit1SpecialRules", unit1SpecialRulesString);
        templateData.addObject("unit2SpecialRules", unit2SpecialRulesString);
        templateData.addObject("unit1", unit1);
        templateData.addObject("unit2", unit2);
        templateData.addObject("combatDescription", combatDescription);

        return templateData;
        }


}
