package me.ductran.monkeytouch.log

import android.util.Log as ALog

object Log {
    @JvmStatic
    fun l(log: String) {
        System.out.println(log)
    }

    @JvmStatic
    fun d(log: String) {
        ALog.d("LOG", log)
    }
}