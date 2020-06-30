package com.framgia.kotlintraining.moviedb.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.RemoteException
import androidx.core.app.NotificationCompat
import com.framgia.kotlintraining.moviedb.R
import com.framgia.kotlintraining.moviedb.di.createApi
import com.framgia.kotlintraining.moviedb.screen.main.MainActivity
import com.framgia.kotlintraining.moviedb.utils.constant.Constants
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        val intent = Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }

        var title = ""
        var body = ""
        var name = ""

        remoteMessage.notification?.let { notification ->
            title = notification.title.toString()
            body = notification.body.toString()
        }

        remoteMessage.data.let { data ->
            name = data["name"].toString()
        }

        intent.apply {
            putExtra(Constants.TITLE, title)
            putExtra(Constants.BODY, body)
            putExtra(Constants.NAME, name)
        }

        try {
            val pendingIntent = PendingIntent.getActivity(
                this.applicationContext, 0 /* Request code */, intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )

            val channelId = getString(R.string.default_notification_channel_id)

            val notificationBuilder = NotificationCompat.Builder(this.applicationContext, channelId)
                .setSmallIcon(R.drawable.default_notification)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    channelId,
                    getString(R.string.notification_channel),
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                notificationManager.createNotificationChannel(channel)
            }
            notificationManager.notify(0, notificationBuilder.build())
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)
        sendRegistrationToServer(token)
    }

    private fun sendRegistrationToServer(token: String) {
        val compositeDisposable = CompositeDisposable()
        val apiService = createApi()

        compositeDisposable.add(
            apiService.registerDeviceToken(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                }, {
                })
        )
    }

    companion object {
        private const val TOPIC = "PUSH_RC"
    }
}