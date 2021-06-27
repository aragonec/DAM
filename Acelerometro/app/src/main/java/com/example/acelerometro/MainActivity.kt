package com.example.acelerometro

import android.content.Context
import android.content.pm.ActivityInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.TextView


class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private lateinit var mAcce: Sensor
    private lateinit var txtCoor: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sensorManager=getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mAcce = sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        txtCoor = findViewById(R.id.txtCoor)
    }

    override fun onResume() {
        super.onResume()
        sensorManager!!.registerListener(this, mAcce, SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onPause() {
        super.onPause()
        sensorManager!!.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}


    override fun onSensorChanged(event: SensorEvent?) {
        if(event != null){
            var sensor = event.sensor
            if(sensor.type == Sensor.TYPE_ACCELEROMETER){
                var x = event.values[0]
                var y = event.values[1]
                var z = event.values[2]
                txtCoor.text = "x: "+x+" Y: "+y+" Z: "+z
            }//llave if acelerometro
        }
    }



}