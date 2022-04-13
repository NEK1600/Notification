package com.example.myapplication

import android.app.*
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CalendarView
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.ViewModel.ViewModelNotify
import com.example.myapplication.databinding.ActivityEditBinding
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.db.model.ModelNotify
import com.example.myapplication.notification.*
import java.util.*

class EditActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener{

    private lateinit var binding: ActivityEditBinding
    var day = 0
    var month = 0
    var year = 0
    var hour = 0
    var minute = 0

    var saveDay = 0
    var saveMonth = 0
    var saveYear = 0
    var saveHour = 0
    var saveMinute = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        pickDate()

        createdChanel()

        binding.button.setOnClickListener { scheduleNotify()
            binding.txTime.text = rand().toString()
        }

    }


    private fun createdChanel(){
        val name = "Notify Chanel"
        val desk = "fsadfa"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelID, name, importance)
        channel.description = desk
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    private fun scheduleNotify(){
        val intent = Intent(applicationContext, NotifyHelper::class.java)
        val title = binding.txTime.text.toString()
        intent.putExtra(titleExtra, title)
        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            rand(),
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val time =  getDateTime2()

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            time,
            pendingIntent
        )

    }


    private fun init(){
        val viewModel = ViewModelProvider(this).get(ViewModelNotify::class.java)

        binding.addNotify.setOnClickListener{
            val titleEdit = binding.editNotify.text.toString()
            viewModel.insert(ModelNotify(title = titleEdit))
            onBackPressed()
        }

    }


    private fun getDateTime() {
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)

    }
    private fun getDateTime2() : Long{
        val cal = Calendar.getInstance()

        cal.set(saveYear, saveMonth, saveDay, saveHour, saveMinute)
        return cal.timeInMillis
    }

    private fun pickDate(){
        binding.addTime.setOnClickListener {
            getDateTime()
            //binding.txTime.text = minute.toString()
            DatePickerDialog(this,this, year,month, day).show()
        }
    }



    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        saveDay=p3
        saveMonth = p2
        saveYear = p1
        getDateTime()
        TimePickerDialog(this, this,hour,minute,true).show()
    }

    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
        saveHour = p1
        saveMinute = p2
        getDateTime()
        binding.txTime.text = "$saveDay-$saveMonth-$saveYear\n Час: $saveHour Минута: $saveMinute"

    }


}





























