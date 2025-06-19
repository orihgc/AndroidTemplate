package com.bytedance.ai.expo.bridge

import android.content.pm.PackageManager
import android.os.Bundle
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.XXPermissions
import com.bytedance.ai.expo.utils.ActivityManager
import expo.modules.core.Promise
import expo.modules.interfaces.permissions.Permissions
import expo.modules.interfaces.permissions.PermissionsResponse
import expo.modules.interfaces.permissions.PermissionsResponseListener
import expo.modules.interfaces.permissions.PermissionsStatus

class TestPermissionsImpl : Permissions {
    override fun getPermissionsWithPromise(promise: Promise?, vararg permissions: String?) {
        ActivityManager.currentActivity?.let {
            XXPermissions.with(it).permission(*permissions).request(object : OnPermissionCallback {
                override fun onGranted(permissions: MutableList<String>, allGranted: Boolean) {
                    promise?.resolve(Bundle().apply {
                        putStringArrayList("grantedPermissions", ArrayList(permissions))
                        putBoolean("allGranted", allGranted)
                    })
                }

                override fun onDenied(permissions: MutableList<String>, never: Boolean) {
                    promise?.resolve(Bundle().apply {
                        putStringArrayList("deniedPermissions", ArrayList(permissions))
                        putBoolean("allGranted", false)
                        putBoolean("never", never)
                    })
                }

            })
        }

    }

    override fun getPermissions(
        response: PermissionsResponseListener?,
        vararg permissions: String?
    ) {
        ActivityManager.currentActivity?.let {
            XXPermissions.with(it).permission(*permissions).request(object : OnPermissionCallback {
                override fun onGranted(permissions: MutableList<String>, allGranted: Boolean) {
                    response?.onResult(
                        mapOf(
                            PermissionsResponse.STATUS_KEY to PermissionsResponse(
                                PermissionsStatus.GRANTED,
                                false
                            )
                        )
                    )
                }

                override fun onDenied(permissions: MutableList<String>, never: Boolean) {}
            })
        }
    }

    override fun askForPermissionsWithPromise(promise: Promise?, vararg permissions: String?) {
        ActivityManager.currentActivity?.let {
            XXPermissions.with(it).permission(*permissions).request(object : OnPermissionCallback {
                override fun onGranted(permissions: MutableList<String>, allGranted: Boolean) {
                    promise?.resolve(Bundle().apply {
                        putStringArrayList("grantedPermissions", ArrayList(permissions))
                        putBoolean("allGranted", allGranted)
                    })
                }

                override fun onDenied(permissions: MutableList<String>, never: Boolean) {
                    promise?.resolve(Bundle().apply {
                        putStringArrayList("deniedPermissions", ArrayList(permissions))
                        putBoolean("allGranted", false)
                        putBoolean("never", never)
                    })
                }
            })
        }
    }

    override fun askForPermissions(
        response: PermissionsResponseListener?,
        vararg permissions: String?
    ) {
        ActivityManager.currentActivity?.let {
            XXPermissions.with(it).permission(*permissions).request(object : OnPermissionCallback {
                override fun onGranted(permissions: MutableList<String>, allGranted: Boolean) {
                    response?.onResult(
                        mapOf(
                            PermissionsResponse.STATUS_KEY to PermissionsResponse(
                                PermissionsStatus.GRANTED,
                                false
                            )
                        )
                    )
                }

                override fun onDenied(permissions: MutableList<String>, never: Boolean) {}
            })
        }
    }

    override fun hasGrantedPermissions(vararg permissions: String): Boolean {
        ActivityManager.currentActivity?.let {
            val pm = it.packageManager
            for (permission in permissions) {
                if (pm.checkPermission(
                        permission,
                        it.packageName
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return false
                }
            }
        }
        return false
    }

    override fun isPermissionPresentInManifest(permission: String?): Boolean {
        val context = ActivityManager.currentActivity?.applicationContext ?: return false
        try {
            context.packageManager.getPackageInfo(
                context.packageName,
                PackageManager.GET_PERMISSIONS
            )?.run {
                return requestedPermissions.contains(permission)
            }
            return false
        } catch (_: PackageManager.NameNotFoundException) {
            return false
        }
    }
}