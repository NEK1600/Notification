package com.example.myapplication

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.ViewModel.ViewModelNotify
import com.example.myapplication.databinding.ActivityEditBinding
import com.example.myapplication.db.model.ModelNotify
import com.example.myapplication.notification.NotifyHelper
import com.example.myapplication.notification.channelID
import com.example.myapplication.notification.rand
import com.example.myapplication.notification.titleExtra
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

        initInsertBd()

        initNotifyButton()
        createdChanel()

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
        val title = binding.editNotify.text.toString()
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

    // подключает бд и записывает в бд
    private fun initInsertBd(){
        val viewModel = ViewModelProvider(this).get(ViewModelNotify::class.java)

        binding.addNotify.setOnClickListener{
            val titleEdit = binding.editNotify.text.toString()
            viewModel.insert(ModelNotify(title = titleEdit,
                timePerson = getDateTimePerson().toString()))

            scheduleNotify()
            onBackPressed()
        }

    }

    //возращает время на данный моменрт
    private fun getDateTimeNow():Date {
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)
        cal.set(year, month, day, hour, minute)
        return cal.time
    }

    //возращает время выбраное пользователем
    private fun getDateTimePerson():String {
        var saveMonth2 = saveMonth+1
        return "$saveDay-$saveMonth2-$saveYear\n Час: $saveHour Минута: $saveMinute"
    }

    private fun getDateTime2() : Long{
        val cal = Calendar.getInstance()
        cal.set(saveYear, saveMonth, saveDay, saveHour, saveMinute)
        return cal.timeInMillis
    }

    private fun initNotifyButton(){
        binding.addTime.setOnClickListener {
            getDateTimeNow()
            DatePickerDialog(this,this, year,month, day).show()
        }

    }


    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        saveDay=p3
        saveMonth = p2
        saveYear = p1
        getDateTimeNow()
        TimePickerDialog(this, this,hour,minute,true).show()
    }

    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
        saveHour = p1
        saveMinute = p2
        getDateTimeNow()
        binding.txTime.text = "$saveDay-$saveMonth-$saveYear\n Час: $saveHour Минута: $saveMinute"

    }


}





























