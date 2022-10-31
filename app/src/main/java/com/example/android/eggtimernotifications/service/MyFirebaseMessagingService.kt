package com.example.android.eggtimernotifications.service

import android.app.NotificationManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.android.eggtimernotifications.util.sendNotification
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.i("Token FCM", "onNewToken: $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        //TODO remove logs
        Log.d("FCM SERVICE", "From: ${message.from}")

        Log.d("FCM SERVICE", "onMessageReceived: ${message.data}")

        message.notification?.let {
            sendNotification(it.body!!)
        }
    }

    private fun sendNotification(messageBody:String) {
        val notificationManager = ContextCompat.getSystemService(
            applicationContext,
            NotificationManager::class.java
        ) as NotificationManager

        notificationManager.sendNotification(messageBody, applicationContext)
    }
}