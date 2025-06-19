package com.bytedance.ai.expo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bytedance.ai.bridge.ModuleRegistry
import com.bytedance.ai.expo.databinding.ActivityMainBinding
import com.bytedance.ai.expo.test.TestExpoApplicationActivity
import com.bytedance.ai.expo.test.TestExpoCameraActivity
import com.bytedance.ai.expo.test.TestExpoHapticsActivity
import com.bytedance.ai.expo.test.TestExpoNetWorkActivity
import expo.modules.application.ApplicationModule
import expo.modules.camera.CameraViewModule
import expo.modules.clipboard.ClipboardModule
import expo.modules.filesystem.FileSystemModule
import expo.modules.filesystem.next.FileSystemNextModule
import expo.modules.haptics.HapticsModule
import expo.modules.network.NetworkModule
import expo.modules.notifications.badge.BadgeModule
import expo.modules.notifications.notifications.background.ExpoBackgroundNotificationTasksModule
import expo.modules.notifications.notifications.categories.ExpoNotificationCategoriesModule
import expo.modules.notifications.notifications.channels.NotificationChannelGroupManagerModule
import expo.modules.notifications.notifications.channels.NotificationChannelManagerModule
import expo.modules.notifications.notifications.emitting.NotificationsEmitter
import expo.modules.notifications.notifications.presentation.ExpoNotificationPresentationModule
import expo.modules.notifications.notifications.scheduling.NotificationScheduler
import expo.modules.notifications.permissions.NotificationPermissionsModule
import expo.modules.notifications.serverregistration.ServerRegistrationModule
import expo.modules.notifications.tokens.PushTokenModule


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnTestCamera.setOnClickListener {
            startActivity(Intent(this, TestExpoCameraActivity::class.java))
        }
        binding.btnTestNetwork.setOnClickListener {
            startActivity(Intent(this, TestExpoNetWorkActivity::class.java))
        }

        binding.btnTestHaptics.setOnClickListener {
            startActivity(Intent(this, TestExpoHapticsActivity::class.java))
        }

        binding.btnTestApplication.setOnClickListener {
            startActivity(Intent(this, TestExpoApplicationActivity::class.java))
        }

    }
}