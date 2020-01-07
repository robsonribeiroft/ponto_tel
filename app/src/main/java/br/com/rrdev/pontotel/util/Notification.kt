package br.com.rrdev.pontotel.util

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import br.com.rrdev.pontotel.ListActivity
import br.com.rrdev.pontotel.PontotelApplication.Companion.application
import br.com.rrdev.pontotel.R

class Notification {

    companion object {
        private const val CHANNEL_ID = "PONTO_TEL_CHANNEL"
        private const val NOTIFICATION_ID = 0
    }

    fun show(){
        val builder = NotificationCompat.Builder(application, CHANNEL_ID)
        builder.run {
            setContentTitle(application.getString(R.string.app_name))
            setContentText(application.getString(R.string.notificacao_texto))
            setAutoCancel(true)
            val intent = Intent(application, ListActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            setContentIntent(PendingIntent.getActivity(application, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT))
        }

        val notificationManager = application.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID, builder.build())
    }
}