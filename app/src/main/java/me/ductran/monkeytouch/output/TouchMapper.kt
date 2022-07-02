package me.ductran.monkeytouch.output

import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import me.ductran.monkeytouch.output.config.TouchConfig

/**
 * Created by shyri on 08/09/17.
 */
class TouchMapper(private val touchConfig: TouchConfig) {
    private val touchSimulator: TouchSimulator
    fun processEvent(event: KeyEvent?) {
        Log.d(LOG_TAG, "Key Event received")
//        for (TouchMapping tapMapping : touchConfig.mappings) {
//            tapMapping.processEvent(event);
//        }
    }

    fun processEvent(event: MotionEvent?) {
        Log.d(LOG_TAG, "Motion Event received")
//        for (TouchMapping tapMapping : touchConfig.mappings) {
//            tapMapping.processEvent(event);
//        }
    }

    companion object {
        private const val LOG_TAG = "TouchMapper"
    }

    init {
        touchSimulator = TouchSimulator()
        for (i in touchConfig.mappings.indices) {
            touchConfig.mappings[i]
                .setPointerId(i)
            touchConfig.mappings[i]
                .setTouchSimulator(touchSimulator)
        }
    }
}