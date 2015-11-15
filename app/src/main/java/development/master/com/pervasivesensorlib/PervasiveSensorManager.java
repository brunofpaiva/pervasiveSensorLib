package development.master.com.pervasivesensorlib;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.ArrayList;

public class PervasiveSensorManager implements SensorEventListener{

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private ArrayList<PervasiveSensorAdapter> myListeners;

    public PervasiveSensorManager(final Context context) {
        myListeners = new ArrayList<>();
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
    }

    /**
     * Register a PervasiveSensorListener on PervasiveSensorManager to receive updates from this
     * sensor if the phone has.
     *
     * @param listener The PervasiveSensorListener that will listen the sensor.
     * @param sensorType Its a constant that identifies what sensor is that.
     * Ex.: sensorType = Sensor.TYPE_ACCELEROMETER;
     * @param sensorUpdateTime Set the interval between sensor updates.
     * Ex.: sensorUpdateTime = SensorManager.SENSOR_DELAY_NORMAL;
     */
    public boolean registerSensor(final PervasiveSensorAdapter listener, final int sensorType,
                               final int sensorUpdateTime) {
        boolean hasSensor = false;
        mSensor = mSensorManager.getDefaultSensor(sensorType);
        if (mSensor != null) {
            mSensorManager.registerListener(this, mSensor, sensorUpdateTime);
            hasSensor = true;
        }
        myListeners.add(listener);
        return hasSensor;
    }

    /**
     * Unregister a specific sensor defined by received sensorType.
     *
     * @param listener The
     *
     * @param sensorType int which defines what Sensor will be unregistered from the
     *      PervasiveSensorManager.
     */
    public void unregisterSensor(final PervasiveSensorListener listener, final int sensorType) {
        mSensorManager.unregisterListener(this, mSensorManager.getDefaultSensor(sensorType));
        myListeners.remove(listener);
    }

    /**
     *
     */
    public void unregisterAllSensors() {
        for (int i = 0; i < myListeners.size(); i++) {
            mSensorManager.unregisterListener(this,
                    mSensorManager.getDefaultSensor(myListeners.get(i).getSensorType()));
        }
        myListeners.clear();
    }

    private void notifyPervasiveSensors(final float[] sensorValues, final int sensorType) {
        for (int i = 0; i < myListeners.size(); i++) {
            if (sensorType == Sensor.TYPE_ACCELEROMETER) {
                myListeners.get(i).OnAccelerometerSensorChanged(sensorValues);
            } else if (sensorType == Sensor.TYPE_AMBIENT_TEMPERATURE) {
                myListeners.get(i).OnAmbientTemperatureSensorChanged(sensorValues);
            } else if (sensorType == Sensor.TYPE_GRAVITY) {
                myListeners.get(i).OnGravitySensorChanged(sensorValues);
            } else if (sensorType == Sensor.TYPE_GYROSCOPE) {
                myListeners.get(i).OnGyroscopeSensorChanged(sensorValues);
            } else if (sensorType == Sensor.TYPE_LIGHT) {
                myListeners.get(i).OnLightSensorChanged(sensorValues);
            } else if (sensorType == Sensor.TYPE_LINEAR_ACCELERATION) {
                myListeners.get(i).OnLinearAccelerationSensorChanged(sensorValues);
            } else if (sensorType == Sensor.TYPE_MAGNETIC_FIELD) {
                myListeners.get(i).OnMagneticFieldSensorChanged(sensorValues);
            } else if (sensorType == Sensor.TYPE_ORIENTATION) {
                myListeners.get(i).OnOrientationSensorChanged(sensorValues);
            } else if (sensorType == Sensor.TYPE_PRESSURE) {
                myListeners.get(i).OnPressureSensorChanged(sensorValues);
            } else if (sensorType == Sensor.TYPE_PROXIMITY) {
                myListeners.get(i).OnProximitySensorChanged(sensorValues);
            } else if (sensorType == Sensor.TYPE_RELATIVE_HUMIDITY) {
                myListeners.get(i).OnRelativeHumiditySensorChanged(sensorValues);
            } else if (sensorType == Sensor.TYPE_ROTATION_VECTOR) {
                myListeners.get(i).OnRotationVectorSensorChanged(sensorValues);
            } else if (sensorType == Sensor.TYPE_TEMPERATURE) {
                myListeners.get(i).OnTemperatureSensorChanged(sensorValues);
            }
        }
    }

    @Override
    public void onSensorChanged(final SensorEvent event) {
        notifyPervasiveSensors(event.values, event.sensor.getType());
    }

    @Override
    public void onAccuracyChanged(final Sensor sensor, final int accuracy) {

    }
}
