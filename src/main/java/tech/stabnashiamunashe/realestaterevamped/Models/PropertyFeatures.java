package tech.stabnashiamunashe.realestaterevamped.Models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PropertyFeatures {

    private Boolean hasBorehole;

    private Integer garagesCarSpots;

    private Integer stories;

    private Boolean hasInternetConnection;

    private Boolean allowsPets;

    private Boolean hasZesa;

    private Boolean hasElectricFence;

    private Boolean hasElectricGate;

    private Boolean hasLandscapedGarden;

    private Boolean hasSwimmingPool;

    private Boolean hasVeranda;

    private Boolean isWalledOrFenced;

    private Boolean hasWaterTanks;

    private Boolean hasAlarmSystem;

    private Boolean hasBuiltInCupboards;

    private Boolean hasCourtyard;

    private Boolean hasFireplace;

    private Boolean hasFittedKitchen;

    private Boolean hasMES;

    private Boolean hasSolarGeyser;

    private Boolean isTiled;

    private Boolean hasWalkInCloset;
}
