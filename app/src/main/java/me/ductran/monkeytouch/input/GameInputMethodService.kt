package me.ductran.monkeytouch.input

import android.inputmethodservice.InputMethodService
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import me.ductran.monkeytouch.GlobalVar
import me.ductran.monkeytouch.output.config.ConfigParser
import me.ductran.monkeytouch.output.config.TouchConfig
import java.io.File
import java.io.FileNotFoundException

/**
 * Created by shyri on 06/09/17.
 */
class GameInputMethodService : InputMethodService() {
    companion object {
        const val TAG = "IMS"
    }

    private var touchConfig: TouchConfig? = null
    private var inputSender: InputSender? = null
    override fun onCreate() {
        super.onCreate()
//        try {
//            touchConfig =
//                readFile("/storage/self/primary/Android/data/es.shyri.touchmapper/files/mapping.json")
//        } catch (e: FileNotFoundException) {
//            e.printStackTrace()
//        }
        touchConfig = GlobalVar.getTouchConfig()
        inputSender = InputSender()
        inputSender!!.start()
    }

    override fun onCreateInputView(): View {
        return super.onCreateInputView()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        Log.d(
            TAG, "Key Down " + event.device
                .descriptor
        )

        inputSender!!.sendKeyEvent(event) // FIXME: Temp
        return true

        if (event.device
                .descriptor
            == touchConfig!!.deviceId
        ) {
            inputSender!!.sendKeyEvent(event)
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        Log.d(TAG, "key up $keyCode ${event.action}")
        inputSender!!.sendKeyEvent(event)
        return true

        if (event.device
                .descriptor
            == touchConfig!!.deviceId
        ) {
            inputSender!!.sendKeyEvent(event)
            return true
        }
        return super.onKeyUp(keyCode, event)
    }

    override fun onGenericMotionEvent(event: MotionEvent): Boolean {
        Log.d(TAG, "motion ${event.x} ${event.y} ${event.action} ${event.actionButton}")
        inputSender!!.sendMotionEvent(event)
        return true

        if (event.device
                .descriptor
            == touchConfig!!.deviceId
        ) {
            inputSender!!.sendMotionEvent(event)
            return true
        }
        return super.onGenericMotionEvent(event)
    }

    @Throws(FileNotFoundException::class)
    private fun readFile(fileName: String): TouchConfig {
        val configParser = ConfigParser()
        return configParser.parseConfig(File(fileName))
    }
}