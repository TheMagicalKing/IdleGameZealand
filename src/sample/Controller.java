package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Controller {

    private double antalmiks=359;
    private int antalClippers, antalFarmere, antalProcessors, antalBuilders, antalBuilderCementMixer;
    private double antalClippersPrice=10, antalFarmerePrice = 60, antalProcessorsPrice = 160, antalBuildersPrice = 360;

    @FXML
    private Label antalClipsLabel, antalClippersLabel, priceClippersLabel, antalFarmereLabel, priceFarmereLabel, antalProcessorsLabel, priceProcessorsLabel, antalBuildersLabel, priceBuildersLabel;






    @FXML
    private Button autoclipperButton, autoFarmerButton, autoProcessorsButton, autoBuildersButton;

    @FXML
    protected void makeMikButtonAction(ActionEvent event) {

        antalmiks++;
        antalClipsLabel.setText(String.valueOf(antalmiks));




    }
    @FXML
    protected void autoclipperButtonAction(ActionEvent event) {

        antalmiks = antalmiks-antalClippersPrice;
        antalClippersPrice = antalClippersPrice+(antalClippers*3.5);
        priceClippersLabel.setText(String.valueOf(antalClippersPrice));
        antalClippers++;
        antalClippersLabel.setText(String.valueOf(antalClippers));
        if (antalmiks <=antalClippersPrice){autoclipperButton.setVisible(false);}


        // longrunning operation runs on different thread
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {
                        mikClipperTask();
                    }
                };

                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }

                    // UI update is run on the Application thread
                    Platform.runLater(updater);

                }
            }

        });
        // don't let thread prevent JVM shutdown
        thread.setDaemon(true);
        thread.start();



    }
    @FXML
    protected void mikFarmerButtonAction(ActionEvent event) {

        antalmiks = antalmiks-antalFarmerePrice;
        antalFarmerePrice = antalFarmerePrice+(2*(antalFarmere*10));
        priceFarmereLabel.setText(String.valueOf(antalFarmerePrice));
        antalFarmere++;
        antalFarmereLabel.setText(String.valueOf(antalFarmere));
        if (antalmiks <=antalFarmerePrice){autoFarmerButton.setVisible(false);}


        // longrunning operation runs on different thread
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {
                        mikFarmerTask();
                    }
                };

                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }

                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }

        });
        // don't let thread prevent JVM shutdown
        thread.setDaemon(true);
        thread.start();



    }
    @FXML
    protected void mikProcessorsAction(ActionEvent event) {

        antalmiks = antalmiks-antalProcessorsPrice;
        antalProcessorsPrice = antalProcessorsPrice+(2*(antalProcessors*10));
        priceProcessorsLabel.setText(String.valueOf(antalProcessorsPrice));
        antalProcessors++;
        antalProcessorsLabel.setText(String.valueOf(antalProcessors));
        if (antalmiks <=antalProcessorsPrice){autoProcessorsButton.setVisible(false);}


        // longrunning operation runs on different thread
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {
                        mikProcessorsTask();
                    }
                };

                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }

                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }

        });
        // don't let thread prevent JVM shutdown
        thread.setDaemon(true);
        thread.start();



    }
    @FXML
    protected void mikBuilderAction(ActionEvent event) {

        antalmiks = antalmiks-antalBuildersPrice;
        antalBuildersPrice = antalBuildersPrice+(2*(antalProcessors*10));
        priceBuildersLabel.setText(String.valueOf(antalBuildersPrice));
        antalBuilders++;
        antalBuildersLabel.setText(String.valueOf(antalBuilders));
        if (antalmiks <=antalBuildersPrice){autoBuildersButton.setVisible(false);}


        // longrunning operation runs on different thread
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {
                        mikBuilderTask();
                    }
                };

                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }

                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }

        });
        // don't let thread prevent JVM shutdown
        thread.setDaemon(true);
        thread.start();



    }

    @FXML
    protected void researchScreen(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../sample/researchMenu.fxml"));
            Parent researchScreenParent = (Parent) fxmlLoader.load();
            Stage researchStage = new Stage();
            researchStage.initModality(Modality.APPLICATION_MODAL);
            researchStage.initStyle(StageStyle.UTILITY);
            researchStage.setTitle("Edit Menu");
            researchStage.setScene(new Scene(researchScreenParent));
            researchStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void mikClipperTask() {
        //todo make upgrades for mikClippers, should be over all less than others
        antalmiks++;
        antalClipsLabel.setText(String.valueOf(antalmiks));    }
    private void mikFarmerTask() {
        //todo make upgrades for mikFarmers, should be more than mikClippers but less than others
        antalmiks= antalmiks+4;
        antalClipsLabel.setText(String.valueOf(antalmiks));    }
    private void mikProcessorsTask() {
        //todo make upgrades for mikProcessorss, should be more than mikClippers and mikFarmers but less than others
        antalmiks= antalmiks+8;
        antalClipsLabel.setText(String.valueOf(antalmiks));    }
    private void mikBuilderTask() {
        //todo make upgrades for mikFarmers, should be more than mikClippers, mikFarmers and mikprocessors but les than more to come.
        antalmiks= antalmiks+(16+(antalBuilderCementMixer*5));
        antalClipsLabel.setText(String.valueOf(antalmiks));    }

        //todo move all upgrades to ResearchController!!!!!

}
