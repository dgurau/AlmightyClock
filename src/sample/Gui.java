package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    static int speedX = 0;
    public static double seconds=0,minutes=0,hours=0;
    public static boolean state = false;

    //TimerTask and Timer
    public static Timer timerClock;
    public static TimerTask taskAnalogClock;

    //The view for the clock Quadrant and hands
    public static ImageView secondHand = new ImageView();
    public static ImageView minuteHand = new ImageView();
    public static ImageView hourHand = new ImageView();
    public static ImageView clockQuadrant = new ImageView();

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("The Almighty Clock");

        //
        timerClock = new Timer();
        taskAnalogClock = new AnalogClockTask();

        //Start and Stop button
        Button startButton = new Button("Start");
        Button stopButton = new Button ("Stop");

        //Faster and Slower Button
        Button travelToFuture = new Button("Faster");
        Button slowTime = new Button ("Slower");

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
        buttonsLayout.getChildren().addAll(startButton,stopButton,travelToFuture,slowTime);

        //The layout for the Analog clock
        StackPane analogClockLayout = new StackPane();
        analogClockLayout.getChildren().addAll(clockQuadrant,minuteHand,hourHand,secondHand);

        //The DigitalClock
        Label digitalClock = new Label();

        //Messages to User
        Label speedMessage = new Label();
        Label timeElapsed = new Label();


        //Main Pane that contains the other layouts
        BorderPane mainLayout = new BorderPane();
        mainLayout.setCenter(analogClockLayout);
        mainLayout.setBottom(buttonsLayout);
        mainLayout.setTop(speedMessage);

        //Start the Tasks
        timerClock.scheduleAtFixedRate(taskAnalogClock,1000,1000);

        //actions for Start and Stop button
        startButton.setOnAction(e -> {
            state = true;
        });

        stopButton.setOnAction(e ->{
            state = false;

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
        });

        //Actions for Slower and Faster button
        travelToFuture.setOnAction(e -> {
            speedX +=60;
            timeElapsed.setText("Time is running " + speedX + " times faster");
            System.out.println("Time is running " + speedX + " times faster");
        });
        slowTime.setOnAction(e -> {
            speedX -=60;
            System.out.println("Time is running " + speedX + " times slower");
            timeElapsed.setText("Time is running " + speedX + " times slower");
        });

        //The Scene
        Scene scene = new Scene(mainLayout,1000,1000);
        window.setScene(scene);
        window.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
