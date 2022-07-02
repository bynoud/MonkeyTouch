package me.ductran.monkeytouch.output.touch;

import android.view.KeyEvent;
import android.view.MotionEvent;

import java.lang.reflect.InvocationTargetException;

import me.ductran.monkeytouch.log.Log;

import static android.view.KeyEvent.ACTION_UP;

/**
 * Created by shyri on 09/09/17.
 */

public class TapMapping extends TouchMapping {
    public static final String KEY_TYPE = "TAP";

    private int keyCode;
    private int x;
    private int y;

    private transient int lastAction = ACTION_UP;

    public TapMapping(int keyCode, int x, int y) {
        this.keyCode = keyCode;
        this.x = x;
        this.y = y;
    }

    public void processEvent(KeyEvent event) {
        Log.l("Processing Event: " + event.getScanCode());

        if (event.getKeyCode() == keyCode && lastAction != event.getAction()) {

            try {
                lastAction = event.getAction();
                touchSimulator.simulateTouch(pointerId, event.getAction(), x, y);
            } catch (InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void processEvent(MotionEvent keyEvent) {

    }
}
