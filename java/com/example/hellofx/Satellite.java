// === Updated: Satellite.java ===
package com.example.hellofx;

public abstract class Satellite {
    protected String name;
    protected boolean isActive;
    protected int intervalSeconds;
    protected String targetRegion;
    protected long lastCaptureTime;

    public Satellite(String name) {
        this.name = name;
        this.isActive = false;
        this.intervalSeconds = 10;
        this.targetRegion = "Global";
        this.lastCaptureTime = System.currentTimeMillis();
    }

    public void activate() {
        isActive = true;
        lastCaptureTime = System.currentTimeMillis();
    }

    public void deactivate() {
        isActive = false;
    }

    public void setInterval(int seconds) {
        this.intervalSeconds = seconds;
    }

    public void setTargetRegion(String region) {
        this.targetRegion = region;
    }

    public abstract String performMission();

    public String tryCaptureImage() {
        if (!isActive) return null;
        long now = System.currentTimeMillis();
        if (now - lastCaptureTime >= intervalSeconds * 1000L) {
            lastCaptureTime = now;
            return name + " captured an image of " + targetRegion + ".";
        }
        return null;
    }

    public int getTimeUntilNextCapture() {
        long now = System.currentTimeMillis();
        long remaining = (intervalSeconds * 1000L) - (now - lastCaptureTime);
        return (int) Math.max(0, remaining / 1000L);
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getTargetRegion() {
        return targetRegion;
    }

    public int getIntervalSeconds() {
        return intervalSeconds;
    }

    @Override
    public String toString() {
        String status = isActive ? "Active" : "Inactive";
        int remaining = getTimeUntilNextCapture();
        return name + " (" + status + ", next in " + remaining + "s)";
    }
}

//