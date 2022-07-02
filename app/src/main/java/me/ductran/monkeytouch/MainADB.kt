package me.ductran.monkeytouch


import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.KeyEvent
import android.view.MotionEvent
import me.ductran.monkeytouch.output.Server
import me.ductran.monkeytouch.output.TouchMapper
import me.ductran.monkeytouch.output.config.ConfigParser
import me.ductran.monkeytouch.output.config.TouchConfig
import java.io.File
import java.io.FileNotFoundException


/**
 * Created by shyri on 06/09/17.
 */


class MainADB(looper: Looper) {
    private var touchMapper: TouchMapper? = null
    var messageHandler: Handler = object : Handler(looper) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                EventInput.SOURCE_MOVEMENT -> touchMapper?.processEvent(msg.obj as MotionEvent)
                EventInput.SOURCE_KEY -> touchMapper?.processEvent(msg.obj as KeyEvent)
            }
        }
    }
    private val server: Server
    @Throws(FileNotFoundException::class)
    private fun readFile(fileName: String): TouchConfig {
        val configParser = ConfigParser()
        return configParser.parseConfig(File(fileName)) // FIXME
    }

    companion object {
        const val DEFAULT_PORT = 6543
        @JvmStatic
        fun main(args: Array<String>) {
            Looper.prepare()
            val looper = Looper.myLooper()
            val main = MainADB(looper!!)
            Looper.loop()
        }
    }

    init {
//        var touchConfig: TouchConfig? = null
//        try {
//            touchConfig =
//                readFile("/storage/self/primary/Android/data/es.shyri.touchmapper/files/mapping.json")
//        } catch (e: FileNotFoundException) {
//            e.printStackTrace()
//        }
        try {
//            touchMapper = TouchMapper(touchConfig) // FIXME
            touchMapper = TouchMapper(TouchConfig()) // FIXME: Temp here
        } catch (e: Exception) {
            e.printStackTrace()
        }
        server = Server(messageHandler)
        server.start()
    }
}
