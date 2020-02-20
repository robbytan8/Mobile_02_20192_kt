package com.robby.mobile_02_20192_kt

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author Robby Tan
 */
class MainActivity : AppCompatActivity() {

    private val requestImageCapture = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_web.setOnClickListener {
            val uri = Uri.parse("http://it.maranatha.edu")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }
        btn_phone.setOnClickListener {
            val uri = Uri.parse("tel:+62222012186")
            val intent = Intent(Intent.ACTION_DIAL, uri)
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }
        btn_map.setOnClickListener {
            // Uri location = Uri.parse("geo:0,0?q=Universitas+Kristen+Maranatha,+Bandung");
            // Or map point based on latitude/longitude
            val location =
                Uri.parse("geo:-6.924884,107.636569?q=-6.924884,107.636569&z=15") // z param is zoom level
            val intent = Intent(Intent.ACTION_VIEW, location)
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }
        btn_camera.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (intent.resolveActivity(packageManager) != null) {
                startActivityForResult(intent, requestImageCapture)
            }
        }
        btn_call_second.setOnClickListener {
            if (et_first_name.text.isEmpty() || et_last_name.text.toString().isEmpty()) {
                Toast.makeText(this, R.string.error_empty, Toast.LENGTH_SHORT).show()
                Snackbar.make(ll_root, R.string.error_empty, Snackbar.LENGTH_SHORT).show()

            } else {
                val bundle = Bundle()
                bundle.putString(MyViewUtils.KEY_FIRST, et_first_name.text.toString())
                bundle.putString(MyViewUtils.KEY_LAST, et_last_name.text.toString())

                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtras(bundle)
                intent.putExtra(Intent.EXTRA_TEXT, et_another_extra.getText().toString())
                startActivity(intent)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == requestImageCapture && resultCode == Activity.RESULT_OK) {
            if (data != null && data.extras != null) {
                val extras = data.extras
                val imageBitmap = extras!!.get("data") as Bitmap
                iv_thumbnail.setImageBitmap(imageBitmap)
            }
        }
    }
}
