package sample;

import javafx.scene.control.Label;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.TimerTask;

/**
 * Created by dgurau on 14.11.2016.
 */
public class DigitalClockTask extends TimerTask{
    @Override
    public void run(){
        DecimalFormat formatter = new DecimalFormat("00");
        String hours = formatter.format(Gui.hours);
        String minutes = formatter.format( Gui.minutes);
        String seconds = formatter.format(Gui.seconds);

        Gui.digitalClock = new Label();
        Gui.digitalClock.setText(seconds);

        if(Gui.seconds == 60){
            Gui.minutes ++;
            Gui.seconds = 0;
        }

        if(Gui.minutes == 60){
            Gui.hours ++;
            Gui.minutes = 0;
        }

        if(Gui.hours == 24){
            Gui.hours = 0;
        }
    }
}
