package me.ductran.monkeytouch.output.touch;

import android.view.KeyEvent;
import android.view.MotionEvent;

import me.ductran.monkeytouch.log.Log;

/**
 * Created by shyri on 09/05/2018.
 */

public class SlideMapping extends TouchMapping {
    public static final String KEY_TYPE = "SLIDE";
    private static final int AXIS_X = 0;
    private static final int AXIS_Y = 0;

    private int axis;
    private int startValue;
    private int endValue;

    @Override
    public void processEvent(KeyEvent keyEvent) {

    }

    @Override
    public void processEvent(MotionEvent keyEvent) {
        Log.l("WARNING, SlideMapping Implementation is not completed");

    }
}
