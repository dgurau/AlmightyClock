package sample;

import java.util.TimerTask;

/**
 * Created by dgurau on 14.11.2016.
 */
public class AnalogClockTask extends TimerTask{

    @Override
    public void run(){

        double angleSeconds,angleMinutes,angleHours;
        while(Gui.state == true){

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Gui.seconds +=Gui.speedX;

            angleSeconds = Gui.seconds*6;
            angleMinutes = angleSeconds/60;
            angleHours = angleMinutes/12;

            Gui.secondHand.setRotate(angleSeconds);
            Gui.minuteHand.setRotate(Gui.minutes*6 + angleMinutes);
            Gui.hourHand.setRotate(Gui.hours*6 + angleHours);

        }

    }
}
