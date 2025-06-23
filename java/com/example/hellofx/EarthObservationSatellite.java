package com.example.hellofx;

public class EarthObservationSatellite extends Satellite {
    public EarthObservationSatellite(String name) {
        super(name);
    }

    @Override
    public String performMission() {
        if (isActive) {
            return name + " is taking Earth images.";
        } else {
            return name + " is inactive.";
        }
    }
}
