package com.ori.rn.learn

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ori.rn.learn.databinding.ActivityMainBinding
import com.ori.rn.learn.test.TestExpoCameraActivity


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