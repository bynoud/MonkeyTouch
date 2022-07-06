package me.ductran.monkeytouch.output

import android.view.KeyEvent
import android.view.MotionEvent
import me.ductran.monkeytouch.log.Log
import me.ductran.monkeytouch.output.config.TouchConfig

/**
 * Created by shyri on 08/09/17.
 */
class TouchMapper(private val touchConfig: TouchConfig) {
    private val touchSimulator: TouchSimulator
    fun processEvent(event: KeyEvent) {
        Log.l("Key Event received ${event.keyCode} ${event.action}")
//        for (TouchMapping tapMapping : touchConfig.mappings) {
//            tapMapping.processEvent(event);
//        }
    }

    fun processEvent(event: MotionEvent) {
        Log.l( "Motion Event received ${event.x} ${event.y} ${event.action} ${event.actionButton}")
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
            touchConfig.mappings[i].setPointerId(i)
            touchConfig.mappings[i].setTouchSimulator(touchSimulator)
        }
    }
}