package me.ductran.monkeytouch

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainAct"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "Started")
        Log.d(TAG, GlobalVar.dataDir)

//        findViewById<Button>(R.id.buttonOpenTest).setOnClickListener(View.OnClickListener {
//            ControllerTestActivity.openActivity(
//                this@MainActivity
//            )
//        })

        findViewById<Button>(R.id.buttonOpenInputMethodSelector).setOnClickListener{
            showInputMethodSelector(it)
        }
    }

    private fun showInputMethodSelector(focus: View) {
        Log.d(TAG, GlobalVar.appDir)
//        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.showSoftInput(focus, InputMethodManager.SHOW_FORCED)
        val imeManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imeManager.showInputMethodPicker()
    }
}