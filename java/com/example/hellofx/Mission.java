package com.example.hellofx;

import java.util.ArrayList;
import java.util.List;

public class Mission {
    private final List<Satellite> satellites = new ArrayList<>();

    public void addSatellite(Satellite s) {
        satellites.add(s);
    }

    public List<String> runMission() {
        List<String> logs = new ArrayList<>();
        for (Satellite s : satellites) {
            logs.add(s.performMission());
        }
        return logs;
    }

    public List<String> simulateCaptureCycle() {
        List<String> logs = new ArrayList<>();
        for (Satellite s : satellites) {
            String result = s.tryCaptureImage();
            if (result != null) {
                logs.add(result);
            }
        }
        return logs;
    }

    public List<Satellite> getSatellites() {
        return satellites;
    }
}
