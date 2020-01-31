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

    private double antalmiks;
    private int antalClippers, antalClippersUpgrade1, antalClippersUpgrade2, antalClippersUpgrade3, antalClippersUpgrade4, antalFarmere, antalFarmereUpgrade1, antalFarmereUpgrade2, antalFarmereUpgrade3, antalFarmereUpgrade4, antalProcessors, antalProcessorsUpgrade1, antalProcessorsUpgrade2, antalProcessorsUpgrade3, antalProcessorsUpgrade4, antalBuilders, antalBuilderUpgrade1, antalBuilderUpgrade2, antalBuilderUpgrade3, antalBuilderUpgrade4;
    //Clipper Price and Clipper Upgrade Prices as well as Clipper Production
    private double antalClippersPrice=10, antalClippersUpgrade1Price=60+(antalClippersUpgrade1*60), antalClippersUpgrade2Price=120+(antalClippersUpgrade2*120), antalClippersUpgrade3Price=240+(antalClippersUpgrade3*240), antalClippersUpgrade4Price=480+(antalClippersUpgrade4*480), clipperProduction=1*antalClippers;
    //Farmer Price and Farmer Upgrade Prices as well as Farmer Production
    private double antalFarmerePrice = 60, antalFarmereUpgrade1Price=360+(antalFarmereUpgrade1*360), antalFarmereUpgrade2Price=720+(antalFarmereUpgrade2*720), antalFarmereUpgrade3Price=1440+(antalFarmereUpgrade3*1440), antalFarmereUpgrade4Price=2880+(antalFarmereUpgrade4*2880), farmProduction=4*antalFarmere;
    //Processor Price and Processor Upgrade Prices as well as Processor Production
    private double antalProcessorsPrice = 160,  antalProcessorsUpgrade1Price=960+(antalProcessorsUpgrade1*960),  antalProcessorsUpgrade2Price=1920+(antalProcessorsUpgrade2*1920),  antalProcessorsUpgrade3Price=3840+(antalProcessorsUpgrade3*3840),  antalProcessorsUpgrade4Price=7680+(antalProcessorsUpgrade4*7680), processorProduction=8*antalProcessors;
    //Builder Price and Builder Upgrade Prices as well as Builder Production
    private double antalBuildersPrice = 360,  antalBuildersUpgrade1Price=2160+(antalBuilderUpgrade1*2160),  antalBuildersUpgrade2Price=4320+(antalBuilderUpgrade2*4320),  antalBuildersUpgrade3Price=8640+(antalBuilderUpgrade3*8640),  antalBuildersUpgrade4Price=17280+(antalBuilderUpgrade4*17280), builderProduction=16*antalBuilders;

    //Game Related stuff, like titles, amount of miks produced, etc

    @FXML
    private Label mikTitleLabel, antalMiksClipsUpgrade, antalMiksFarmUpgrade, antalMiksProcUpgrade, antalMiksBuildUpgrade;
    //Clippers Labels
    @FXML
    private Label antalClipsLabelMain, antalClippersLabel, priceClippersLabel, mikClipUpgradeLabel, antalMikClippersUpgrade1, antalMikClippersUpgrade2, antalMikClippersUpgrade3, antalMikClippersUpgrade4;
    //Farmers Labels
    @FXML
    private Label antalFarmereLabel, priceFarmereLabel, mikFarmUpgradeLabel, antalMikFarmUpgrade1, antalMikFarmUpgrade2, antalMikFarmUpgrade3, antalMikFarmUpgrade4;
    //Processors Labels
    @FXML
    private Label antalProcessorsLabel, priceProcessorsLabel, mikProcessUpgradeLabel, antalMikProcUpgrade1, antalMikProcUpgrade2, antalMikProcUpgrade3, antalMikProcUpgrade4;
    //Builders Labels
    @FXML
    private Label  antalBuildersLabel, priceBuildersLabel, mikBuildUpgradeLabel, antalMikBuildUpgrade1, antalMikBuildUpgrade2, antalMikBuildUpgrade3, antalMikBuildUpgrade4;
    @FXML //Mainscreen buttons
    private Button autoclipperButton, autoFarmerButton, autoProcessorsButton, autoBuildersButton, startButton;
    @FXML//Mik Clipper Upgrade Buttons
    private Button mikClipperUpg1, mikClipperUpg2, mikClipperUpg3, mikClipperUpg4;
    @FXML//Mik Farmer Upgrade Buttons
    private Button mikFarmerUpg1, mikFarmerUpg2, mikFarmerUpg3, mikFarmerUpg4;
    @FXML//Mik Processor Upgrade Buttons
    private Button mikProcUpg1, mikProcUpg2, mikProcUpg3, mikProcUpg4;
    @FXML//Mik Builder Upgrade Buttons
    private Button mikBuildUpg1, mikBuildUpg2, mikBuildUpg3, mikBuildUpg4;
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
                        for (int i=0; i<5;i++){
                            switch (i){
                                case 0:
                                    uiUpdate();
                                    break;
                                case 1:
                                    mikBuilderTask();
                                    break;
                                case 2:
                                    mikFarmerTask();
                                    break;
                                case 3:
                                    mikClipperTask();
                                    break;
                                case 4:
                                    mikProcessorsTask();
                                    break;
                            }
                        }
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
    protected void uiUpdate(){

        if (antalmiks >= antalClippersPrice) {
            autoclipperButton.setVisible(true);
        } else if (antalmiks < antalClippersPrice) {
            autoclipperButton.setVisible(false);
        }
        if (antalmiks >= antalFarmerePrice) {
            autoFarmerButton.setVisible(true);
        } else if (antalmiks < antalFarmerePrice) {
            autoFarmerButton.setVisible(false);
        }
        if (antalmiks >= antalProcessorsPrice) {
            autoProcessorsButton.setVisible(true);
        } else if (antalmiks < antalProcessorsPrice) {
            autoProcessorsButton.setVisible(false);
        }
        if (antalmiks >= antalBuildersPrice) {
            autoBuildersButton.setVisible(true);
        } else if (antalmiks < antalBuildersPrice) {
            autoBuildersButton.setVisible(false);
        }
        if (antalmiks >= antalClippersUpgrade1Price){
            mikClipperUpg1.setVisible(true);
        }else if (antalmiks < antalClippersUpgrade1Price){
            mikClipperUpg1.setVisible(false);
        }if (antalmiks >= antalClippersUpgrade2Price){
            mikClipperUpg2.setVisible(true);
        }else if (antalmiks < antalClippersUpgrade2Price){
            mikClipperUpg2.setVisible(false);
        }if (antalmiks >= antalClippersUpgrade3Price){
            mikClipperUpg3.setVisible(true);
        }else if (antalmiks < antalClippersUpgrade3Price){
            mikClipperUpg3.setVisible(false);
        }if (antalmiks >= antalClippersUpgrade4Price){
            mikClipperUpg4.setVisible(true);
        }else if (antalmiks < antalClippersUpgrade4Price){
            mikClipperUpg4.setVisible(false);
        }if (antalmiks >= antalFarmereUpgrade1Price){
            mikFarmerUpg1.setVisible(true);
        }else if (antalmiks < antalFarmereUpgrade1Price){
            mikFarmerUpg1.setVisible(false);
        }if (antalmiks >= antalFarmereUpgrade2Price){
            mikFarmerUpg2.setVisible(true);
        }else if (antalmiks < antalFarmereUpgrade2Price){
            mikFarmerUpg2.setVisible(false);
        }if (antalmiks >= antalFarmereUpgrade3Price){
            mikFarmerUpg3.setVisible(true);
        }else if (antalmiks < antalFarmereUpgrade3Price){
            mikFarmerUpg3.setVisible(false);
        }if (antalmiks >= antalFarmereUpgrade4Price){
            mikFarmerUpg4.setVisible(true);
        }else if (antalmiks < antalFarmereUpgrade4Price){
            mikFarmerUpg4.setVisible(false);
        }
        if (antalmiks >= antalProcessorsUpgrade1Price){
            mikProcUpg1.setVisible(true);
        }else if (antalmiks < antalProcessorsUpgrade1Price){
            mikProcUpg1.setVisible(false);
        }if (antalmiks >= antalProcessorsUpgrade2Price){
            mikProcUpg2.setVisible(true);
        }else if (antalmiks < antalProcessorsUpgrade2Price){
            mikProcUpg2.setVisible(false);
        }if (antalmiks >= antalProcessorsUpgrade3Price){
            mikProcUpg3.setVisible(true);
        }else if (antalmiks < antalProcessorsUpgrade3Price){
            mikProcUpg3.setVisible(false);
        }if (antalmiks >= antalProcessorsUpgrade4Price){
            mikProcUpg4.setVisible(true);
        }else if (antalmiks < antalProcessorsUpgrade4Price){
            mikProcUpg4.setVisible(false);
        }
        if (antalmiks >= antalBuildersUpgrade1Price){
            mikBuildUpg1.setVisible(true);
        }else if (antalmiks < antalBuildersUpgrade1Price){
            mikBuildUpg1.setVisible(false);
        }if (antalmiks >= antalBuildersUpgrade2Price){
            mikBuildUpg2.setVisible(true);
        }else if (antalmiks < antalBuildersUpgrade2Price){
            mikBuildUpg2.setVisible(false);
        }if (antalmiks >= antalBuildersUpgrade3Price){
            mikBuildUpg3.setVisible(true);
        }else if (antalmiks < antalBuildersUpgrade3Price){
            mikBuildUpg3.setVisible(false);
        }if (antalmiks >= antalBuildersUpgrade4Price){
            mikBuildUpg4.setVisible(true);
        }else if (antalmiks < antalBuildersUpgrade4Price){
            mikBuildUpg4.setVisible(false);
        }

    }

    @FXML
    protected void changeClipperPaneTrue(ActionEvent event){
        mikClipperPane.setVisible(true);
        gridPaneGame.setVisible(false);
        mikClipUpgradeLabel.setVisible(true);
        mikTitleLabel.setVisible(false);
    }
    @FXML
    protected void changeFarmerPaneTrue(ActionEvent event){
        mikFarmerPane.setVisible(true);
        gridPaneGame.setVisible(false);
        mikFarmUpgradeLabel.setVisible(true);
        mikTitleLabel.setVisible(false);
    }
    @FXML
    protected void changeProcessorPaneTrue(ActionEvent event){
        mikProcessorPane.setVisible(true);
        gridPaneGame.setVisible(false);
        mikProcessUpgradeLabel.setVisible(true);
        mikTitleLabel.setVisible(false);
    }
    @FXML
    protected void changeBuilderPaneTrue(ActionEvent event){
        mikBuilderPane.setVisible(true);
        gridPaneGame.setVisible(false);
        mikBuildUpgradeLabel.setVisible(true);
        mikTitleLabel.setVisible(false);
    }
    @FXML
    protected void changeClipperPaneFalse(ActionEvent event){
        mikClipperPane.setVisible(false);
        gridPaneGame.setVisible(true);
        mikClipUpgradeLabel.setVisible(false);
        mikTitleLabel.setVisible(true);
    }
    @FXML
    protected void changeFarmerPaneFalse(ActionEvent event){
        mikFarmerPane.setVisible(false);
        gridPaneGame.setVisible(true);
        mikFarmUpgradeLabel.setVisible(false);
        mikTitleLabel.setVisible(true);
    }
    @FXML
    protected void changeProcessorPaneFalse(ActionEvent event){
        mikProcessorPane.setVisible(false);
        gridPaneGame.setVisible(true);
        mikProcessUpgradeLabel.setVisible(false);
        mikTitleLabel.setVisible(true);
    }
    @FXML
    protected void changeBuilderPaneFalse(ActionEvent event){
        mikBuilderPane.setVisible(false);
        gridPaneGame.setVisible(true);
        mikBuildUpgradeLabel.setVisible(false);
        mikTitleLabel.setVisible(true);
    }

    @FXML
    protected void makeMikButtonAction(ActionEvent event) {

        antalmiks++;
        antalClipsLabelMain.setText(String.valueOf(antalmiks));







    }
    //anything related to Mik Clippers
    @FXML
    protected void autoclipperButtonAction(ActionEvent event) {

        antalmiks = antalmiks-antalClippersPrice;
        antalClippersPrice = antalClippersPrice+(antalClippers*3.5);
        priceClippersLabel.setText(String.valueOf(antalClippersPrice));
        antalClippers++;
        clipperProduction=1*antalClippers;
        antalClippersLabel.setText(String.valueOf(antalClippers));
        if (antalmiks <=antalClippersPrice){autoclipperButton.setVisible(false);}
    }
    @FXML
    protected void mikClipperUpgrade1(){
        antalmiks=antalmiks-antalClippersUpgrade1Price;
        antalClippersUpgrade1++;
        antalMikClippersUpgrade1.setText(String.valueOf(antalClippersUpgrade1));
    }
    @FXML
    protected void mikClipperUpgrade2(){
        antalmiks=antalmiks-antalClippersUpgrade2Price;
        antalClippersUpgrade2++;
        antalMikClippersUpgrade2.setText(String.valueOf(antalClippersUpgrade2));
    }
    @FXML
    protected void mikClipperUpgrade3(){
        antalmiks=antalmiks-antalClippersUpgrade3Price;
        antalClippersUpgrade3++;
        antalMikClippersUpgrade3.setText(String.valueOf(antalClippersUpgrade3));
    }
    @FXML
    protected void mikClipperUpgrade4(){
        antalmiks=antalmiks-antalClippersUpgrade4Price;
        antalClippersUpgrade4++;
        antalMikClippersUpgrade4.setText(String.valueOf(antalClippersUpgrade4));
    }


    //anything related to Mik Farmers
    @FXML
    protected void mikFarmerButtonAction(ActionEvent event) {

        antalmiks = antalmiks-antalFarmerePrice;
        antalFarmerePrice = antalFarmerePrice+(2*(antalFarmere*10));
        priceFarmereLabel.setText(String.valueOf(antalFarmerePrice));
        antalFarmere++;
        farmProduction=4*antalFarmere;
        antalFarmereLabel.setText(String.valueOf(antalFarmere));
        if (antalmiks <=antalFarmerePrice){autoFarmerButton.setVisible(false);}
}
    @FXML
    protected void mikFarmerUpgrade1(){
        antalmiks=antalmiks-antalFarmereUpgrade1Price;
        antalFarmereUpgrade1++;
        antalMikFarmUpgrade1.setText(String.valueOf(antalFarmereUpgrade1));
    }
    @FXML
    protected void mikFarmerUpgrade2(){
        antalmiks=antalmiks-antalFarmereUpgrade2Price;
        antalFarmereUpgrade2++;
        antalMikFarmUpgrade2.setText(String.valueOf(antalFarmereUpgrade2));
    }
    @FXML
    protected void mikFarmerUpgrade3(){
        antalmiks=antalmiks-antalFarmereUpgrade3Price;
        antalFarmereUpgrade3++;
        antalMikFarmUpgrade1.setText(String.valueOf(antalFarmereUpgrade3));
    }
    @FXML
    protected void mikFarmerUpgrade4(){
        antalmiks=antalmiks-antalFarmereUpgrade4Price;
        antalFarmereUpgrade4++;
        antalMikFarmUpgrade4.setText(String.valueOf(antalFarmereUpgrade4));
    }

    //anything related to Mik Processors
    @FXML
    protected void mikProcessorsAction(ActionEvent event) {

        antalmiks = antalmiks-antalProcessorsPrice;
        antalProcessorsPrice = antalProcessorsPrice+(2*(antalProcessors*10));
        priceProcessorsLabel.setText(String.valueOf(antalProcessorsPrice));
        antalProcessors++;
        processorProduction=8*antalProcessors;
        antalProcessorsLabel.setText(String.valueOf(antalProcessors));
        if (antalmiks <=antalProcessorsPrice){autoProcessorsButton.setVisible(false);}
 }
    @FXML
    protected void mikProcessorUpgrade1(){
        antalmiks=antalmiks-antalProcessorsUpgrade1Price;
        antalProcessorsUpgrade1++;
        antalMikProcUpgrade1.setText(String.valueOf(antalProcessorsUpgrade1));
    }
    @FXML
    protected void mikProcessorUpgrade2(){
        antalmiks=antalmiks-antalProcessorsUpgrade2Price;
        antalProcessorsUpgrade2++;
        antalMikProcUpgrade2.setText(String.valueOf(antalProcessorsUpgrade2));
    }
    @FXML
    protected void mikProcessorUpgrade3(){
        antalmiks=antalmiks-antalProcessorsUpgrade3Price;
        antalProcessorsUpgrade3++;
        antalMikProcUpgrade3.setText(String.valueOf(antalProcessorsUpgrade3));
    }
    @FXML
    protected void mikProcessorUpgrade4(){
        antalmiks=antalmiks-antalProcessorsUpgrade4Price;
        antalProcessorsUpgrade4++;
        antalMikProcUpgrade4.setText(String.valueOf(antalProcessorsUpgrade4));
    }

    //anything related to Mik Builders
    @FXML
    protected void mikBuilderAction(ActionEvent event) {

        antalmiks = antalmiks-antalBuildersPrice;
        antalBuildersPrice = antalBuildersPrice+(2*(antalProcessors*10));
        priceBuildersLabel.setText(String.valueOf(antalBuildersPrice));
        antalBuilders++;
        builderProduction=16*antalBuilders;
        antalBuildersLabel.setText(String.valueOf(antalBuilders));
        if (antalmiks <=antalBuildersPrice){autoBuildersButton.setVisible(false);}
        }
    @FXML
    protected void mikBuilderUpgrade1(){
        antalmiks=antalmiks-antalBuildersUpgrade1Price;
        antalBuilderUpgrade1++;
        antalMikBuildUpgrade1.setText(String.valueOf(antalBuilderUpgrade1));
    }
    @FXML
    protected void mikBuilderUpgrade2(){
        antalmiks=antalmiks-antalBuildersUpgrade2Price;
        antalBuilderUpgrade2++;
        antalMikBuildUpgrade1.setText(String.valueOf(antalBuilderUpgrade2));
    }
    @FXML
    protected void mikBuilderUpgrade3(){
        antalmiks=antalmiks-antalBuildersUpgrade3Price;
        antalBuilderUpgrade3++;
        antalMikBuildUpgrade1.setText(String.valueOf(antalBuilderUpgrade3));
    }
    @FXML
    protected void mikBuilderUpgrade4(){
        antalmiks=antalmiks-antalBuildersUpgrade4Price;
        antalBuilderUpgrade4++;
        antalMikBuildUpgrade1.setText(String.valueOf(antalBuilderUpgrade4));
    }




    private void mikClipperTask() {
        //todo make upgrades for mikClippers, should be over all less than others
        antalmiks = antalmiks+(clipperProduction+(antalClippersUpgrade1*5)+(antalClippersUpgrade2*10)+(antalClippersUpgrade3*15)+(antalClippersUpgrade4*20));
        antalClipsLabelMain.setText(String.valueOf(antalmiks));
        antalMiksClipsUpgrade.setText(String.valueOf(antalmiks));
        antalMiksFarmUpgrade.setText(String.valueOf(antalmiks));
        antalMiksProcUpgrade.setText(String.valueOf(antalmiks));
        antalMiksBuildUpgrade.setText(String.valueOf(antalmiks));
        }
    private void mikFarmerTask() {
        //todo make upgrades for mikFarmers, should be more than mikClippers but less than others
        antalmiks= antalmiks+(farmProduction+(antalFarmereUpgrade1*5)+(antalFarmereUpgrade2*10)+(antalFarmereUpgrade3*15)+(antalFarmereUpgrade4*20));
        antalClipsLabelMain.setText(String.valueOf(antalmiks));
        antalMiksClipsUpgrade.setText(String.valueOf(antalmiks));
        antalMiksFarmUpgrade.setText(String.valueOf(antalmiks));
        antalMiksProcUpgrade.setText(String.valueOf(antalmiks));
        antalMiksBuildUpgrade.setText(String.valueOf(antalmiks));
    }
    private void mikProcessorsTask() {
        //todo make upgrades for mikProcessorss, should be more than mikClippers and mikFarmers but less than others
        antalmiks= antalmiks+(processorProduction+(antalProcessorsUpgrade1*5)+(antalProcessorsUpgrade2*10)+(antalProcessorsUpgrade3*15)+(antalProcessorsUpgrade4*20));
        antalClipsLabelMain.setText(String.valueOf(antalmiks));
        antalMiksClipsUpgrade.setText(String.valueOf(antalmiks));
        antalMiksFarmUpgrade.setText(String.valueOf(antalmiks));
        antalMiksProcUpgrade.setText(String.valueOf(antalmiks));
        antalMiksBuildUpgrade.setText(String.valueOf(antalmiks));
        }
    private void mikBuilderTask() {
        //todo make upgrades for mikFarmers, should be more than mikClippers, mikFarmers and mikprocessors but les than more to come.
        antalmiks= antalmiks+(builderProduction+(antalBuilderUpgrade1*5)+(antalBuilderUpgrade2*10)+(antalBuilderUpgrade3*15)+(antalBuilderUpgrade4*20));
        antalClipsLabelMain.setText(String.valueOf(antalmiks));
        antalMiksClipsUpgrade.setText(String.valueOf(antalmiks));
        antalMiksFarmUpgrade.setText(String.valueOf(antalmiks));
        antalMiksProcUpgrade.setText(String.valueOf(antalmiks));
        antalMiksBuildUpgrade.setText(String.valueOf(antalmiks));
        }
}