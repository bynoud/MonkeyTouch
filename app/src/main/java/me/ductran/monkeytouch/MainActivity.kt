package me.ductran.monkeytouch

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
//        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.showSoftInput(focus, InputMethodManager.SHOW_FORCED)
        val imeManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imeManager.showInputMethodPicker()
    }
}