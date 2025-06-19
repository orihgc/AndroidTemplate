package com.bytedance.ai.expo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bytedance.ai.expo.databinding.ActivityMainBinding
import com.bytedance.ai.expo.test.TestExpoCameraActivity


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnTestCamera.setOnClickListener {
            startActivity(Intent(this, TestExpoCameraActivity::class.java))
        }
    }
}