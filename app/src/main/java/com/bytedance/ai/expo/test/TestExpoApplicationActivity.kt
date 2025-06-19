package com.bytedance.ai.expo.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bytedance.ai.expo.bridge.SimulationJsRuntime
import com.bytedance.ai.expo.databinding.ActivityApplicationTestBinding

class TestExpoApplicationActivity:AppCompatActivity() {
    private lateinit var binding: ActivityApplicationTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApplicationTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            getInstallationTimeAsync.setOnClickListener {
                SimulationJsRuntime.mockFeCallToNative("ExpoApplication.getInstallationTimeAsync")
            }
            getLastUpdateTimeAsync.setOnClickListener {
                SimulationJsRuntime.mockFeCallToNative("ExpoApplication.getLastUpdateTimeAsync")
            }
            getInstallReferrerAsync.setOnClickListener {
                SimulationJsRuntime.mockFeCallToNative("ExpoApplication.getInstallReferrerAsync")
            }
        }
    }
}