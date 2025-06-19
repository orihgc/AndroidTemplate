package com.bytedance.ai.expo.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bytedance.ai.expo.bridge.SimulationJsRuntime
import com.bytedance.ai.expo.databinding.ActivityNetworkTestBinding

class TestExpoNetWorkActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNetworkTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNetworkTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            getNetworkStateAsync.setOnClickListener {
                SimulationJsRuntime.mockFeCallToNative("ExpoNetwork.getNetworkStateAsync")
            }
            getIpAddressAsync.setOnClickListener {
                SimulationJsRuntime.mockFeCallToNative("ExpoNetwork.getIpAddressAsync")
            }
            isAirplaneModeEnabledAsync.setOnClickListener {
                SimulationJsRuntime.mockFeCallToNative("ExpoNetwork.isAirplaneModeEnabledAsync")
            }
        }
    }
}