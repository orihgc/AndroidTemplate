package com.ori.rn.learn.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ori.rn.learn.bridge.SimulationJsRuntime
import com.ori.rn.learn.databinding.ActivityCameraTestBinding

class TestExpoCameraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCameraTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.requestCameraPermission.setOnClickListener {
            SimulationJsRuntime.mockFeCallToNative("ExpoCamera.requestCameraPermissionsAsync")
        }

        binding.requestMicrophonePermission.setOnClickListener {
            SimulationJsRuntime.mockFeCallToNative("ExpoCamera.requestMicrophonePermissionsAsync")
        }

        binding.toggleRecordingAsyncAvailable.setOnClickListener {
            SimulationJsRuntime.mockFeCallToNative("ExpoCamera.toggleRecordingAsyncAvailable")
        }

        binding.isModernBarcodeScannerAvailable.setOnClickListener {
            SimulationJsRuntime.mockFeCallToNative("ExpoCamera.isModernBarcodeScannerAvailable")
        }
    }
}