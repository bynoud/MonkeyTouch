package me.ductran.monkeytouch.output;

import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

import me.ductran.monkeytouch.output.config.TouchConfig;
import me.ductran.monkeytouch.output.touch.TouchMapping;

/**
 * Created by shyri on 08/09/17.
 */

public class TouchMapperOrg {
    private final static String LOG_TAG = "TouchMapper";
    private final TouchSimulator touchSimulator;
    private final TouchConfig touchConfig;

    public TouchMapperOrg(TouchConfig touchConfig) throws Exception {
        this.touchConfig = touchConfig;

        touchSimulator = new TouchSimulator();

        for (int i = 0; i < touchConfig.mappings.size(); i++) {
            touchConfig.mappings.get(i)
                                .setPointerId(i);
            touchConfig.mappings.get(i)
                                .setTouchSimulator(touchSimulator);
        }
    }

    public void processEvent(KeyEvent event) {
        Log.d(LOG_TAG, "Key Event received");
//        for (TouchMapping tapMapping : touchConfig.mappings) {
//            tapMapping.processEvent(event);
//        }
    }

    public void processEvent(MotionEvent event) {
        Log.d(LOG_TAG, "Motion Event received");
//        for (TouchMapping tapMapping : touchConfig.mappings) {
//            tapMapping.processEvent(event);
//        }
    }
}
