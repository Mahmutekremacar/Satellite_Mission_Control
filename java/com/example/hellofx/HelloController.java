package com.example.hellofx;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Duration;

public class HelloController {
    @FXML private TextArea logArea;
    @FXML private TextField nameField;
    @FXML private TextField intervalField;
    @FXML private TextField regionField;
    @FXML private ListView<Satellite> satelliteList;

    private final Mission mission = new Mission();
    private final ObservableList<Satellite> satelliteObservableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        satelliteList.setItems(satelliteObservableList);
        setupCaptureLogger();
    }

    private void setupCaptureLogger() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            for (String log : mission.simulateCaptureCycle()) {
                logArea.appendText(log + "\n");
            }
            satelliteList.refresh();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void refreshSatelliteList() {
        Satellite selected = satelliteList.getSelectionModel().getSelectedItem();
        satelliteList.refresh();
        satelliteList.getSelectionModel().select(selected);
    }

    @FXML
    protected void onCreateSatelliteClicked() {
        String name = nameField.getText();
        int interval = Integer.parseInt(intervalField.getText());
        String region = regionField.getText();

        EarthObservationSatellite s = new EarthObservationSatellite(name);
        s.setInterval(interval);
        s.setTargetRegion(region);
        mission.addSatellite(s);
        satelliteObservableList.add(s);
        logArea.appendText("Created: " + s.getName() + "\n");
    }

    @FXML
    protected void onActivateClicked() {
        Satellite selected = satelliteList.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.activate();
            logArea.appendText("Activated: " + selected.getName() + "\n");
            refreshSatelliteList();
        }
    }

    @FXML
    protected void onDeactivateClicked() {
        Satellite selected = satelliteList.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.deactivate();
            logArea.appendText("Deactivated: " + selected.getName() + "\n");
            refreshSatelliteList();
        }
    }

    @FXML
    protected void onRunMissionClicked() {
        logArea.clear();
        for (String log : mission.runMission()) {
            logArea.appendText(log + "\n");
        }
    }
}
