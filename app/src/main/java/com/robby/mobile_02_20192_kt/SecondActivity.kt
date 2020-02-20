package com.robby.mobile_02_20192_kt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*

/**
 * @author Robby Tan
 */
class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val bundle = intent.extras
        if (bundle != null && !bundle.isEmpty) {
            val firstName = bundle.getString(MyViewUtils.KEY_FIRST)
            val lastName = bundle.getString(MyViewUtils.KEY_LAST)
            tv_output.text = String.format("%s %s", firstName, lastName)
        }
    }
}
