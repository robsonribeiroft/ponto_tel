package br.com.rrdev.pontotel.util

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import br.com.rrdev.pontotel.ListActivity
import br.com.rrdev.pontotel.R

class Notification {

    companion object {
        private const val CHANNEL_ID = "PONTO_TEL_CHANNEL"
        private const val NOTIFICATION_ID = 0
    }

    fun show(context: Context){
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
        builder.run {
            setContentTitle(context.getString(R.string.app_name))
            setContentText(context.getString(R.string.notificacao_texto))
            setAutoCancel(true)
            val intent = Intent(context, ListActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            setContentIntent(PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT))
        }

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID, builder.build())
    }
}