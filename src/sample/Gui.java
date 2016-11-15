package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class Gui extends Application {

    Stage window;
    static int speedX = 1;
    public static double seconds=0,minutes=0,hours=0;

    //Variable to check if the clock should Start or Stop
    public static boolean state = false;

    //TimerTask and Timer
    public static Timer timerClock;
    public static TimerTask taskAnalogClock;
    public static TimerTask taskDigitalClock;

    //The view for the clock Quadrant and hands
    public static ImageView secondHand = new ImageView();
    public static ImageView minuteHand = new ImageView();
    public static ImageView hourHand = new ImageView();
    public static ImageView clockQuadrant = new ImageView();

    //The DigitalClock
    public static Label digitalClock;

    @Override
    public void start(Stage primaryStage) throws Exception{

        /**
         * Setting up the GUI
         */

        //Setting Stage
        window = primaryStage;
        window.setTitle("The Almighty Clock");

        //Timers and TimerTasks
        timerClock = new Timer();
        taskAnalogClock = new AnalogClockTask();
        taskDigitalClock = new DigitalClockTask();

        //Start and Stop button
        Button startButton = new Button("Start");
        Button stopButton = new Button ("Stop");

        //Faster and Slower Button
        Button travelToFuture = new Button("Faster");
        Button slowTime = new Button ("Slower");

        //Speed input field
        TextField speedValue = new TextField();
        speedValue.setText("1");

        //Hopefully the clock Quadrant
        Image clock = new Image("sample/images/emptyQuadrant.png");
        Image limb1 = new Image("sample/images/MinuteOnly.png");
        Image limb2 = new Image("sample/images/HourOnly.png");
        Image limb3 = new Image("sample/images/SecondOnly.png");

        //Set images for the views
        clockQuadrant.setImage(clock);
        secondHand.setImage(limb3);
        minuteHand.setImage(limb1);
        hourHand.setImage(limb2);

        //Layout for buttons
        HBox buttonsLayout = new HBox(30);
        buttonsLayout.getChildren().addAll(startButton,stopButton,travelToFuture,speedValue,slowTime);

        //The layout for the Analog clock
        StackPane analogClockLayout = new StackPane();
        analogClockLayout.getChildren().addAll(clockQuadrant,minuteHand,hourHand,secondHand);

        //The DigitalClock
        Label digitalClock = new Label();

        //Messages to User
        Label speedMessage = new Label();
        Label timeElapsed = new Label();

        digitalClock.setText("digital clock maybe");

        //Main Pane that contains the other layouts
        BorderPane mainLayout = new BorderPane();
        mainLayout.setCenter(analogClockLayout);
        mainLayout.setBottom(buttonsLayout);
        mainLayout.setTop(speedMessage);
        mainLayout.setLeft(digitalClock);

        /**
         * Actions that the application does
         */
        //Start the Tasks
        timerClock.scheduleAtFixedRate(taskAnalogClock,1000,1000);
        //timerClock.scheduleAtFixedRate(taskDigitalClock,1000,1000);

        //actions for Start and Stop button
        startButton.setOnAction(e -> {
            state = true;
        });

        stopButton.setOnAction(e ->{
            state = false;
            resetTimer();
        });

        //Actions for Slower and Faster button
        travelToFuture.setOnAction(e -> {

            speedMessage.setText("Time is passing " + speedX + " times faster");
        });

        slowTime.setOnAction(e -> {

            speedMessage.setText("Time is passing " + speedX + " times slower");
        });

        //The Scene
        Scene scene = new Scene(mainLayout,1000,1000);
        window.setScene(scene);
        window.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    //Method that resets timer (angle values and time values)
    public void resetTimer(){
        //Commenting this Thread sleep line will cause a Bug that will set the seconds Hand to 1 when clicking Stop
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        seconds = 0;
        hours = 0;
        minutes = 0;
        secondHand.setRotate(0);
        minuteHand.setRotate(0);
        hourHand.setRotate(0);
    }

}
