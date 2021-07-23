package dev.emg.mvx

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton
import dev.emg.mvx.mvvm.MVVMActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mvvmButton = findViewById<MaterialButton>(R.id.mvvm_button)
        mvvmButton.setOnClickListener {
            val intent = Intent(this, MVVMActivity::class.java)
            startActivity(intent)
        }
    }
}