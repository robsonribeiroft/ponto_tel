package br.com.rrdev.pontotel.service

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log


class AlarmHelper: Service() {


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.action = INTENT_ACTION
        Log.d("check", "start commmand")
        setAlarm()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun setAlarm() {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val i = Intent(this, AlarmHelper::class.java)
        val pi = PendingIntent.getBroadcast(this, 0, i, 0)
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, (1000*60*1).toLong(), pi)
    }

    fun cancelAlarm() {
        val intent = Intent(this, AlarmHelper::class.java)
        val sender = PendingIntent.getBroadcast(this, 0, intent, 0)
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(sender)
    }

    companion object {
        const val INTENT_ACTION = "ALARM_NOTIFY_ACTION"
    }
}