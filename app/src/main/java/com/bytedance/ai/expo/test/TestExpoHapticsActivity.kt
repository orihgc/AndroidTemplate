package com.bytedance.ai.expo.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bytedance.ai.expo.bridge.SimulationJsRuntime
import com.bytedance.ai.expo.databinding.ActivityHapticsTestBinding
import com.google.gson.JsonObject

class TestExpoHapticsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHapticsTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHapticsTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            selectionAsync.setOnClickListener {
                SimulationJsRuntime.mockFeCallToNative("ExpoHaptics.selectionAsync")
            }
            notificationAsync.setOnClickListener {
                SimulationJsRuntime.mockFeCallToNative(
                    "ExpoHaptics.notificationAsync",
                    params = JsonObject().apply {
                        addProperty("type", "success")
                    })
            }
            impactAsync.setOnClickListener {
                SimulationJsRuntime.mockFeCallToNative(
                    "ExpoHaptics.impactAsync",
                    params = JsonObject().apply {
                        addProperty("style", "heavy")
                    })
            }
            performHapticsAsync.setOnClickListener {
                SimulationJsRuntime.mockFeCallToNative(
                    "ExpoHaptics.performHapticsAsync",
                    params = JsonObject().apply {
                        addProperty("type", "confirm")
                    })
            }
        }
    }
}