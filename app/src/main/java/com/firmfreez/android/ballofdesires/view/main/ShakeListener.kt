package com.firmfreez.android.ballofdesires.view.main

import android.content.Context
import android.hardware.*
import timber.log.Timber
import java.lang.UnsupportedOperationException
import kotlin.math.abs

class ShakeListener(private val context: Context) : SensorEventListener {
    private var sensorManager: SensorManager? = null
    var shakeListener: OnShakeListener? = null

    private var shakeCount: Float = 0.0f
    private var lastX: Float = -1.0f
    private var lastY: Float = -1.0f
    private var lastZ: Float = -1.0f
    private var lastShake: Long = 0
    private var lastForce: Long = 0
    private var lastTime: Long = 0

    init {
        resume()
    }

    fun resume() {
        sensorManager = (context.getSystemService(Context.SENSOR_SERVICE) as? SensorManager)
        if(sensorManager == null) {
            throw UnsupportedOperationException("Sensor is not supported")
        }
        sensorManager?.registerListener(this, sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME)
    }

    fun pause() {
        if(sensorManager != null) {
            sensorManager?.unregisterListener(this, sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER))
            sensorManager = null
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(event?.sensor != sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)) {
            return
        }
        val now = System.currentTimeMillis()

        Timber.d("CUR_THRESHOLD: ${now - lastTime}  CUR_SHAKE_TIMEOUT: ${now - lastForce}")

        if(now - lastTime > TIME_THRESHOLD) {
            val diff = now - lastTime
            val speed = event?.let {
                abs(it.values[0] + it.values[1] + it.values[2] - lastX - lastY - lastZ) / diff * 10000
            }
            Timber.d( "SPEED: ${speed}")
            if(speed?: 0.0f > FORCE_THRESHOLD) {
                if(now - lastShake > SHAKE_DURATION) {
                    shakeCount += 1.0f
                    shakeListener?.onShake(shakeCount / SHAKE_COUNT)
                    if(shakeCount > SHAKE_COUNT) {
                        shakeListener?.onComplete()
                        lastShake = now
                        shakeCount = 0.0f
                    }
                }
                lastForce = now
            }
            lastTime = now
            event?.values?.let {
                lastX = it[0]
                lastY = it[1]
                lastZ = it[2]
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}


    interface OnShakeListener {
        fun onShake(progress: Float)
        fun onComplete()
    }

    private companion object {
        const val FORCE_THRESHOLD = 1000
        const val TIME_THRESHOLD  = 100
        const val SHAKE_DURATION  = 1000
        const val SHAKE_COUNT     = 10.0f
    }
}