package dev.emg.mvx

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.emg.mvx.databinding.ActivityMainBinding
import dev.emg.mvx.mvvm.MVVMActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mvvmButton.setOnClickListener {
            val intent = Intent(this, MVVMActivity::class.java)
            startActivity(intent)
        }
    }
}