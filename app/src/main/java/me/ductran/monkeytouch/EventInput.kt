package me.ductran.monkeytouch


import android.hardware.input.InputManager
import android.os.SystemClock
import android.view.InputEvent
import android.view.MotionEvent
import android.view.MotionEvent.PointerCoords
import android.view.MotionEvent.PointerProperties
import androidx.core.view.InputDeviceCompat
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method


/**
 * Created by shyri on 05/09/17.
 */
class EventInput {
    var injectInputEventMethod: Method
    var im: InputManager
    @Throws(InvocationTargetException::class, IllegalAccessException::class)
    fun injectTouch(
        action: Int,
        pointerProperties: Array<PointerProperties?>,
        pointerCoords: Array<PointerCoords?>?
    ) {
        val timeMs = SystemClock.uptimeMillis()
        val event = MotionEvent.obtain(
            timeMs, timeMs, action, pointerProperties.size, pointerProperties, pointerCoords, 0, 0,
            1.0f, 1.0f, 0, 0, InputDeviceCompat.SOURCE_TOUCHSCREEN, 0
        )
        event.source = InputDeviceCompat.SOURCE_TOUCHSCREEN
        injectInputEventMethod.invoke(im, *arrayOf(event, Integer.valueOf(2)))
    }

    val inputDevices: IntArray
        get() = im.inputDeviceIds

    companion object {
        const val SOURCE_KEY = 2
        const val SOURCE_MOVEMENT = 1
    }

    init {
        //Get the instance of InputManager class using reflection
        var methodName = "getInstance"
        val objArr = arrayOfNulls<Any>(0)
        im = InputManager::class.java.getDeclaredMethod(methodName, *arrayOfNulls(0))
            .invoke(null, *objArr) as InputManager

        //Make MotionEvent.obtain() method accessible
        methodName = "obtain"
        MotionEvent::class.java.getDeclaredMethod(methodName, *arrayOfNulls(0)).isAccessible =
            true

        //Get the reference to injectInputEvent method
        methodName = "injectInputEvent"
        injectInputEventMethod =
            InputManager::class.java.getMethod(
                methodName,
                *arrayOf(InputEvent::class.java, Integer.TYPE)
            )
    }
}
