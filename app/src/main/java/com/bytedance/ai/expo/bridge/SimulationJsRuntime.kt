package com.bytedance.ai.expo.bridge

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.core.util.Consumer
import com.bytedance.ai.bridge.AIBridge
import com.bytedance.ai.bridge.ExpoMethodSeeker
import com.bytedance.ai.bridge.ModuleRegistry
import com.bytedance.ai.bridge.protocol.AbsAIBridgePort
import com.bytedance.ai.bridge.protocol.model.PostMessageWrapper
import com.bytedance.ai.bridge.protocol.model.ProtocolMessage
import com.bytedance.ai.bridge.protocol.model.ReceiveMessageWrapper
import com.bytedance.ai.bridge.protocol.model.TargetEntity
import com.bytedance.ai.bridge.utils.CacheHandle
import com.google.gson.JsonObject
import com.bytedance.ai.expo.utils.ActivityManager
import expo.modules.application.ApplicationModule
import expo.modules.camera.CameraViewModule
import expo.modules.clipboard.ClipboardModule
import expo.modules.core.interfaces.ActivityProvider
import expo.modules.filesystem.FileSystemModule
import expo.modules.filesystem.next.FileSystemNextModule
import expo.modules.haptics.HapticsModule
import expo.modules.kotlin.AppContext
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

object SimulationJsRuntime {

    const val TAG = "SimulationJsRuntime"

    private val remoteMessageCache = CacheHandle<ProtocolMessage>()

    private val aiBridge = AIBridge()
    private var callbackId = 0

    init {
        aiBridge.addMethodSeeker(ExpoMethodSeeker)
        aiBridge.start(object : AbsAIBridgePort() {
            override fun realPostMessage(messageWrapper: PostMessageWrapper) {
                Log.i(TAG, "realPostMessage: ${messageWrapper.message.params.toString()}")
                ActivityManager.currentActivity?.let {
                    Toast.makeText(it, messageWrapper.message.params.toString(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun realSetOnMessageCallback(consumer: Consumer<ReceiveMessageWrapper>) {
                remoteMessageCache.setConsumer {
                    consumer.accept(ReceiveMessageWrapper(it))
                }
            }
        })
        setupExpo()
        notifyReady()
    }

    private fun setupExpo(){
        AppContext.permissions = TestPermissionsImpl()
        AppContext.activityProvider =
            ActivityProvider {
                ActivityManager.currentActivity
                    ?: throw IllegalStateException("No current activity")
            }
        ModuleRegistry.registerModule(CameraViewModule())
        ModuleRegistry.registerModule(ApplicationModule())
        ModuleRegistry.registerModule(BadgeModule())
        ModuleRegistry.registerModule(ClipboardModule())
        ModuleRegistry.registerModule(ExpoBackgroundNotificationTasksModule())
        ModuleRegistry.registerModule(ExpoNotificationPresentationModule())
        ModuleRegistry.registerModule(ExpoNotificationCategoriesModule())
        ModuleRegistry.registerModule(FileSystemModule())
        ModuleRegistry.registerModule(FileSystemNextModule())
        ModuleRegistry.registerModule(HapticsModule())
        ModuleRegistry.registerModule(NetworkModule())
        ModuleRegistry.registerModule(NotificationChannelGroupManagerModule())
        ModuleRegistry.registerModule(NotificationChannelManagerModule())
        ModuleRegistry.registerModule(NotificationPermissionsModule())
        ModuleRegistry.registerModule(NotificationScheduler())
        ModuleRegistry.registerModule(NotificationsEmitter())
        ModuleRegistry.registerModule(PushTokenModule())
        ModuleRegistry.registerModule(ServerRegistrationModule())
    }

    private fun notifyReady(){
        remoteMessageCache.offer(
            ProtocolMessage(
                "__bridge_ready__",
                null,
                null,
                TargetEntity(scope = TargetEntity.Scope.System, target = TargetEntity.Target.Bridge),
                ProtocolMessage.Type.Event,
                System.currentTimeMillis()
            )
        )
    }

    fun mockFeCallToNative(name: String, params: JsonObject? = null) {
        remoteMessageCache.offer(
            ProtocolMessage(
                name,
                params,
                ++callbackId,
                TargetEntity(scope = TargetEntity.Scope.System, target = TargetEntity.Target.Bridge),
                ProtocolMessage.Type.Call,
                System.currentTimeMillis()
            )
        )
    }


}