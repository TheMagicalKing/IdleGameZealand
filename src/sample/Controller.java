package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {

    private double antalmiks = 160;
    private int antalClippers, antalFarmere, antalProcessors, antalBuilders;
    private double antalClippersPrice=10, antalFarmerePrice = 60, antalProcessorsPrice = 160, antalBuildersPrice = 360;

    @FXML
    private Label antalClipsLabel, antalClippersLabel, priceClippersLabel, antalFarmereLabel, priceFarmereLabel, antalProcessorsLabel, priceProcessorsLabel, antalBuildersLabel, priceBuildersLabel;






    @FXML
    private Button autoclipperButton, autoFarmerButton, autoProcessorsButton, autoBuildersButton;


    @FXML
    protected void makeMikButtonAction(ActionEvent event) {

        antalmiks++;
        antalClipsLabel.setText(String.valueOf(antalmiks));

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
        }


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


    private void mikClipperTask() {
        antalmiks++;
        antalClipsLabel.setText(String.valueOf(antalmiks));    }
    private void mikFarmerTask() {
        antalmiks= antalmiks+4;
        antalClipsLabel.setText(String.valueOf(antalmiks));    }
    private void mikProcessorsTask() {
        antalmiks= antalmiks+8;
        antalClipsLabel.setText(String.valueOf(antalmiks));    }
    private void mikBuilderTask() {
        antalmiks= antalmiks+16;
        antalClipsLabel.setText(String.valueOf(antalmiks));    }

}
