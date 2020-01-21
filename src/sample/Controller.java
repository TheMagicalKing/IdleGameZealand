package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Controller {

    private double antalmiks=359;
    private int antalClippers, antalClippersUpgrade1, antalClippersUpgrade2, antalClippersUpgrade3, antalClippersUpgrade4, antalFarmere, antalFarmereUpgrade1, antalFarmereUpgrade2, antalFarmereUpgrade3, antalFarmereUpgrade4, antalProcessors, antalProcessorsUpgrade1, antalProcessorsUpgrade2, antalProcessorsUpgrade3, antalProcessorsUpgrade4, antalBuilders, antalBuilderUpgrade1, antalBuilderUpgrade2, antalBuilderUpgrade3, antalBuilderUpgrade4;
    //Clipper Price and Clipper Upgrade Prices
    private double antalClippersPrice=10, antalClippersUpgrade1Price=60+(antalClippersUpgrade1*60), antalClippersUpgrade2Price=120+(antalClippersUpgrade2*120), antalClippersUpgrade3Price=240+(antalClippersUpgrade3*240), antalClippersUpgrade4Price=480+(antalClippersUpgrade4*480);
    //Farmer Price and Farmer Upgrade Prices
    private double antalFarmerePrice = 60, antalFarmereUpgrade1Price=360+(antalFarmereUpgrade1*360), antalFarmereUpgrade2Price=720+(antalFarmereUpgrade2*720), antalFarmereUpgrade3Price=1440+(antalFarmereUpgrade3*1440), antalFarmereUpgrade4Price=2880+(antalFarmereUpgrade4*2880);
    //Processor Price and Processor Upgrade Prices
    private double antalProcessorsPrice = 160,  antalProcessorsUpgrade1Price=960+(antalProcessorsUpgrade1*960),  antalProcessorsUpgrade2Price=1920+(antalProcessorsUpgrade2*1920),  antalProcessorsUpgrade3Price=3840+(antalProcessorsUpgrade3*3840),  antalProcessorsUpgrade4Price=7680+(antalProcessorsUpgrade4*7680);
    //Builder Price and
    private double antalBuildersPrice = 360,  antalBuildersUpgrade1Price=2160+(antalBuilderUpgrade1*2160),  antalBuildersUpgrade2Price=4320+(antalBuilderUpgrade2*4320),  antalBuilderUpgrade3Price=8640+(antalBuilderUpgrade3*8640),  antalBuildersUpgrade4Price=17280+(antalBuilderUpgrade4*17280);

    //Clippers Labels
    @FXML
    private Label antalClipsLabelMain, antalClippersLabel, priceClippersLabel ;
    //Farmers Labels
    @FXML
    private Label antalFarmereLabel, priceFarmereLabel;
    //Processors Labels
    @FXML
    private Label antalProcessorsLabel, priceProcessorsLabel;
    //Builders Labels
    @FXML
    private Label  antalBuildersLabel, priceBuildersLabel, producedBuildersLabel;
    @FXML
    private Button autoclipperButton, autoFarmerButton, autoProcessorsButton, autoBuildersButton, mikBuilderUpg1, startButton, clippersUpgradePaneButton, farmersUpgradePaneButton, processorsUpgradePaneButton, buildersUpgradePaneButton;
    @FXML
    private GridPane startGridPane, gridPaneGame, mikBuilderPane, mikProcessorPane, mikFarmerPane, mikClipperPane;
    @FXML
    protected void startEngine(ActionEvent event){
        //hide the start game button
        startButton.setVisible(false);
        startGridPane.setVisible(false);
        gridPaneGame.setVisible(true);
        // longrunning operation runs on different thread
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {

                    }
                };

                while (true) {
                    try {
                        Thread.sleep(10);

                    } catch (InterruptedException ex) {
                    }
                    uiUpdate();
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
    protected void uiUpdate(){
        if (antalmiks >= antalClippersPrice) {
            autoclipperButton.setVisible(true);
        } else if (antalmiks <= antalClippersPrice) {
            autoclipperButton.setVisible(false);
        }
        if (antalmiks >= antalFarmerePrice) {
            autoFarmerButton.setVisible(true);
        } else if (antalmiks <= antalFarmerePrice) {
            autoFarmerButton.setVisible(false);
        }
        if (antalmiks >= antalProcessorsPrice) {
            autoProcessorsButton.setVisible(true);
        } else if (antalmiks <= antalProcessorsPrice) {
            autoProcessorsButton.setVisible(false);
        }
        if (antalmiks >= antalBuildersPrice) {
            autoBuildersButton.setVisible(true);
        } else if (antalmiks <= antalBuildersPrice) {
            autoBuildersButton.setVisible(false);
        }if (antalmiks >= antalBuildersPrice) {
            mikBuilderUpg1.setVisible(true);
        } else if (antalmiks <= antalBuildersPrice) {
            mikBuilderUpg1.setVisible(false);
        }
        producedBuildersLabel.setText(toString().valueOf(antalBuildersPrice));
    }

    @FXML
    protected void makeMikButtonAction(ActionEvent event) {

        antalmiks++;
        antalClipsLabelMain.setText(String.valueOf(antalmiks));

        if (antalmiks >= antalClippersPrice) {
            autoclipperButton.setVisible(true);
        } else if (antalmiks <= antalClippersPrice) {
            autoclipperButton.setVisible(false);
        }
        if (antalmiks >= antalFarmerePrice) {
            autoFarmerButton.setVisible(true);
        } else if (antalmiks <= antalFarmerePrice) {
            autoFarmerButton.setVisible(false);
        }
        if (antalmiks >= antalProcessorsPrice) {
            autoProcessorsButton.setVisible(true);
        } else if (antalmiks <= antalProcessorsPrice) {
            autoProcessorsButton.setVisible(false);
        }
        if (antalmiks >= antalBuildersPrice) {
            autoBuildersButton.setVisible(true);
        } else if (antalmiks <= antalBuildersPrice) {
            autoBuildersButton.setVisible(false);
        }if (antalmiks >= antalBuildersPrice) {
            mikBuilderUpg1.setVisible(true);
        } else if (antalmiks <= antalBuildersPrice) {
            mikBuilderUpg1.setVisible(false);
        }

        producedBuildersLabel.setText(toString().valueOf((16+antalBuildersPrice*5)));



    }
    //anything related to Mik Clippers
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
                    uiUpdate();
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
    protected void mikClipperUpgrade1(){
        antalmiks=antalmiks-antalClippersUpgrade1Price;
        antalClippersUpgrade1++;
        //antalClippersUpgrade1;
    }
    @FXML
    protected void mikClipperUpgrade2(){
        antalClippersUpgrade2++;
    }
    @FXML
    protected void mikClipperUpgrade3(){
        antalClippersUpgrade3++;
    }
    @FXML
    protected void mikClipperUpgrade4(){
        antalClippersUpgrade4++;
    }


    //anything related to Mik Farmers
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
    protected void mikFarmerUpgrade1(){
        antalFarmereUpgrade1++;
    }
    @FXML
    protected void mikFarmerUpgrade2(){
        antalFarmereUpgrade2++;
    }
    @FXML
    protected void mikFarmerUpgrade3(){
        antalFarmereUpgrade3++;
    }
    @FXML
    protected void mikFarmerUpgrade4(){
        antalFarmereUpgrade4++;
    }

    //anything related to Mik Processors
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
    protected void mikProcessorUpgrade1(){
        antalProcessorsUpgrade1++;
    }
    @FXML
    protected void mikProcessorUpgrade2(){
        antalProcessorsUpgrade2++;
    }
    @FXML
    protected void mikProcessorUpgrade3(){
        antalProcessorsUpgrade3++;
    }
    @FXML
    protected void mikProcessorUpgrade4(){
        antalProcessorsUpgrade4++;
    }

    //anything related to Mik Builders
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
    protected void mikBuilderUpgrade1(){
        antalBuilderUpgrade1++;
    }
    @FXML
    protected void mikBuilderUpgrade2(){
        antalBuilderUpgrade2++;
    }
    @FXML
    protected void mikBuilderUpgrade3(){
        antalBuilderUpgrade3++;
    }
    @FXML
    protected void mikBuilderUpgrade4(){
        antalBuilderUpgrade4++;
    }

    @FXML //Todo figure out how to properly use this
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
        antalmiks = antalmiks+(1+(antalClippersUpgrade1*5)+(antalClippersUpgrade2*10)+(antalClippersUpgrade3*15)+(antalClippersUpgrade4*20));
        antalClipsLabelMain.setText(String.valueOf(antalmiks));    }
    private void mikFarmerTask() {
        //todo make upgrades for mikFarmers, should be more than mikClippers but less than others
        antalmiks= antalmiks+(4+(antalFarmereUpgrade1*5)+(antalFarmereUpgrade2*10)+(antalFarmereUpgrade3*15)+(antalFarmereUpgrade4*20));
        antalClipsLabelMain.setText(String.valueOf(antalmiks));    }
    private void mikProcessorsTask() {
        //todo make upgrades for mikProcessorss, should be more than mikClippers and mikFarmers but less than others
        antalmiks= antalmiks+(8+(antalProcessorsUpgrade1*5)+(antalProcessorsUpgrade2*10)+(antalProcessorsUpgrade3*15)+(antalProcessorsUpgrade4*20));
        antalClipsLabelMain.setText(String.valueOf(antalmiks));    }
    private void mikBuilderTask() {
        //todo make upgrades for mikFarmers, should be more than mikClippers, mikFarmers and mikprocessors but les than more to come.
        antalmiks= antalmiks+(16+(antalBuilderUpgrade1*5)+(antalBuilderUpgrade2*10)+(antalBuilderUpgrade3*15)+(antalBuilderUpgrade4*20));
        antalClipsLabelMain.setText(String.valueOf(antalmiks));    }



}
