package me.ductran.monkeytouch

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.DisplayMetrics
import android.util.Log
import android.view.MotionEvent
import me.ductran.monkeytouch.output.TouchMapper
import me.ductran.monkeytouch.output.config.TouchConfig
import me.ductran.monkeytouch.output.touch.CircleMapping

class GlobalVar : Application() {
    override fun onCreate() {
        super.onCreate()
        app = this
    }

    companion object {
        var app: Application? = null
            private set
        val context: Context
            get() = app!!.applicationContext

        val sharedPref: SharedPreferences
            get() = context
                .getSharedPreferences(
                    context.getString(R.string.pref_file),
                    Context.MODE_PRIVATE
                )

        fun getScreenSize(): List<Int> {
            val metrics: DisplayMetrics =
                context.resources.displayMetrics
            return listOf(metrics.widthPixels, metrics.heightPixels)
        }

        fun setBool(resId: Int, value: Boolean) = with(sharedPref.edit()) {
            putBoolean(context.getString(resId), value)
            apply()
        }
        fun getBool(resId: Int, default: Boolean=false) = sharedPref.getBoolean(context.getString(resId), default)

        fun setString(resId: Int, value: String) = with(sharedPref.edit()) {
            putString(context.getString(resId), value)
            apply()
        }
        fun getString(resId: Int, default: String="") = sharedPref.getString(context.getString(resId), default)!!

        val dataDir: String get() = context.cacheDir.path
        val appDir: String get() = context.applicationInfo.sourceDir
        val libDir: String get() = context.applicationInfo.nativeLibraryDir

        fun getTouchConfig(): TouchConfig {
            // FIXME: Temp here
            val c = CircleMapping(500,1400, 300)
            c.axis_x = MotionEvent.AXIS_X
            c.axis_y = MotionEvent.AXIS_Y
            val t = TouchConfig()
            t.deviceId = "a718a782d34bc767f4689c232d64d527998ea7fd"
            t.mappings = listOf(c)
            Log.d("T", "call here ${t.mappings.size}")
            return t
        }
    }
}