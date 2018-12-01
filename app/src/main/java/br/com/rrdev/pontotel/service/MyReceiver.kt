package br.com.rrdev.pontotel.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import br.com.rrdev.pontotel.util.Notification

class MyReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            if (it.action == AlarmHelper.INTENT_ACTION){
                val not = Notification()
                if (context!=null) not.show(context)
            }
        }
    }
}