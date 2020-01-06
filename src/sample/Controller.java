package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {

    private double antalmiks = 60;
    private int antalClippers = 0, antalFarmere = 0;
    private double antalClippersPrice=10, antalFarmerePrice = 60;

    @FXML
    private Label antalClipsLabel;

    @FXML
    private Label antalClippersLabel;

    @FXML
    private Label antalClippersLabel2;

    @FXML
    private Label antalFarmereLabel;

    @FXML
    private Label antalFarmereLabel2;

    @FXML
    private Button autoclipperButton;

    @FXML
    private Button autoFarmerButton;

    @FXML
    protected void makeMikButtonAction(ActionEvent event) {

        antalmiks++;
        antalClipsLabel.setText(String.valueOf(antalmiks));

        if (antalmiks >=antalClippersPrice) {
            autoclipperButton.setVisible(true);
        }if (antalmiks >= antalFarmerePrice){
            autoFarmerButton.setVisible(true);
        }

    }

    @FXML
    protected void autoclipperButtonAction(ActionEvent event) {

        antalmiks = antalmiks-antalClippersPrice;
        antalClippersPrice = 10+(antalClippers*3.5);
        antalClippersLabel2.setText(String.valueOf(antalClippersPrice));
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
        antalFarmerePrice = 60+(2*(antalFarmere*10));
        antalFarmereLabel2.setText(String.valueOf(antalFarmerePrice));
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
                        myTask();
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
    private void myTask() {
        antalmiks= antalmiks+4;
        antalClipsLabel.setText(String.valueOf(antalmiks));    }

}
