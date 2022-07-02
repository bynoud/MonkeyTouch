package me.ductran.monkeytouch.log

/**
 * Created by shyri on 08/09/17.
 */
import android.util.Log as ALog

object Log {
    @JvmStatic
    fun l(log: String) {
        //        println(log)
        ALog.d("LOG", log)
    }
}