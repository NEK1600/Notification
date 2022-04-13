package com.example.myapplication.notification


import android.app.Notification
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.myapplication.R
import kotlin.random.Random


const val channelID = "channel1"
const val titleExtra = "testName"
//const val  notificationID = 2
fun rand() : Int {
    return Random.nextInt(1,10)
}

class NotifyHelper : BroadcastReceiver(){
    override fun onReceive(context: Context, intent: Intent) {
        val notification = NotificationCompat.Builder(context, channelID)
            .setSmallIcon(R.drawable.group)
            .setContentTitle(intent.getStringExtra(titleExtra))
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(rand(), notification)
    }


}



































