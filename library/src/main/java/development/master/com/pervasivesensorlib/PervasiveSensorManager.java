package development.master.com.pervasivesensorlib;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import development.master.com.pervasivesensorlib.sensors.AccelerometerSensor;
import development.master.com.pervasivesensorlib.sensors.AmbientTemperatureSensor;
import development.master.com.pervasivesensorlib.sensors.GravitySensor;
import development.master.com.pervasivesensorlib.sensors.GyroscopeSensor;
import development.master.com.pervasivesensorlib.sensors.LightSensor;
import development.master.com.pervasivesensorlib.sensors.LinearAccelerationSensor;
import development.master.com.pervasivesensorlib.sensors.MagneticFieldSensor;
import development.master.com.pervasivesensorlib.sensors.OrientationSensor;
import development.master.com.pervasivesensorlib.sensors.PressureSensor;
import development.master.com.pervasivesensorlib.sensors.ProximitySensor;
import development.master.com.pervasivesensorlib.sensors.RelativeHumiditySensor;
import development.master.com.pervasivesensorlib.sensors.RotationVectorSensor;
import development.master.com.pervasivesensorlib.sensors.TemperatureSensor;

public class PervasiveSensorManager implements SensorEventListener{

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private ArrayList<PervasiveSensorAdapter> myListeners;
    private Map<Integer, Boolean> mAvailableSensors = new HashMap<Integer, Boolean>();
    private static final int DEFAULT_UPDATE_TIME = SensorManager.SENSOR_DELAY_NORMAL;

    public PervasiveSensorManager(final Context context, final SensorCallback sensorCallback,
                                  final ArrayList<Integer> sensors) {
        myListeners = new ArrayList<>();
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);

        int currentSensor = -1;
        for (int i=0; i < sensors.size(); i++) {
            currentSensor = sensors.get(i);

            switch (currentSensor) {
                case Sensor.TYPE_ACCELEROMETER:
                    final AccelerometerSensor accelerometerSensor = new AccelerometerSensor(sensorCallback);
                    mAvailableSensors.put(Sensor.TYPE_ACCELEROMETER, registerSensor(accelerometerSensor, Sensor.TYPE_ACCELEROMETER, DEFAULT_UPDATE_TIME));
                    break;
                case Sensor.TYPE_AMBIENT_TEMPERATURE:
                    final AmbientTemperatureSensor ambientTemperatureSensor = new AmbientTemperatureSensor(sensorCallback);
                    mAvailableSensors.put(Sensor.TYPE_AMBIENT_TEMPERATURE, registerSensor(ambientTemperatureSensor, Sensor.TYPE_AMBIENT_TEMPERATURE, DEFAULT_UPDATE_TIME));
                    break;
                case Sensor.TYPE_GRAVITY:
                    final GravitySensor gravitySensor = new GravitySensor(sensorCallback);
                    mAvailableSensors.put(Sensor.TYPE_GRAVITY, registerSensor(gravitySensor, Sensor.TYPE_GRAVITY, DEFAULT_UPDATE_TIME));
                    break;
                case Sensor.TYPE_GYROSCOPE:
                    final GyroscopeSensor gyroscopeSensor = new GyroscopeSensor(sensorCallback);
                    mAvailableSensors.put(Sensor.TYPE_GYROSCOPE, registerSensor(gyroscopeSensor, Sensor.TYPE_GYROSCOPE, DEFAULT_UPDATE_TIME));
                    break;
                case Sensor.TYPE_LIGHT:
                    final LightSensor lightSensor = new LightSensor(sensorCallback);
                    mAvailableSensors.put(Sensor.TYPE_LIGHT, registerSensor(lightSensor, Sensor.TYPE_LIGHT, DEFAULT_UPDATE_TIME));
                    break;
                case Sensor.TYPE_LINEAR_ACCELERATION:
                    final LinearAccelerationSensor linearAccelerationSensor = new LinearAccelerationSensor(sensorCallback);
                    mAvailableSensors.put(Sensor.TYPE_LINEAR_ACCELERATION, registerSensor(linearAccelerationSensor, Sensor.TYPE_LINEAR_ACCELERATION, DEFAULT_UPDATE_TIME));
                    break;
                case Sensor.TYPE_MAGNETIC_FIELD:
                    final MagneticFieldSensor magneticFieldSensor = new MagneticFieldSensor(sensorCallback);
                    mAvailableSensors.put(Sensor.TYPE_MAGNETIC_FIELD, registerSensor(magneticFieldSensor, Sensor.TYPE_MAGNETIC_FIELD, DEFAULT_UPDATE_TIME));
                    break;
                case Sensor.TYPE_ORIENTATION:
                    final OrientationSensor orientationSensor = new OrientationSensor(sensorCallback);
                    mAvailableSensors.put(Sensor.TYPE_ORIENTATION, registerSensor(orientationSensor, Sensor.TYPE_ORIENTATION, DEFAULT_UPDATE_TIME));
                    break;
                case Sensor.TYPE_PRESSURE:
                    final PressureSensor pressureSensor = new PressureSensor(sensorCallback);
                    mAvailableSensors.put(Sensor.TYPE_PRESSURE, registerSensor(pressureSensor, Sensor.TYPE_PRESSURE, DEFAULT_UPDATE_TIME));
                    break;
                case Sensor.TYPE_PROXIMITY:
                    final ProximitySensor proximitySensor = new ProximitySensor(sensorCallback);
                    mAvailableSensors.put(Sensor.TYPE_PROXIMITY, registerSensor(proximitySensor, Sensor.TYPE_PROXIMITY, DEFAULT_UPDATE_TIME));
                    break;
                case Sensor.TYPE_RELATIVE_HUMIDITY:
                    final RelativeHumiditySensor relativeHumiditySensor = new RelativeHumiditySensor(sensorCallback);
                    mAvailableSensors.put(Sensor.TYPE_RELATIVE_HUMIDITY, registerSensor(relativeHumiditySensor, Sensor.TYPE_RELATIVE_HUMIDITY, DEFAULT_UPDATE_TIME));
                    break;
                case Sensor.TYPE_ROTATION_VECTOR:
                    final RotationVectorSensor rotationVectorSensor = new RotationVectorSensor(sensorCallback);
                    mAvailableSensors.put(Sensor.TYPE_ROTATION_VECTOR, registerSensor(rotationVectorSensor, Sensor.TYPE_ROTATION_VECTOR, DEFAULT_UPDATE_TIME));
                    break;
                case Sensor.TYPE_TEMPERATURE:
                    final TemperatureSensor temperatureSensor = new TemperatureSensor(sensorCallback);
                    mAvailableSensors.put(Sensor.TYPE_TEMPERATURE, registerSensor(temperatureSensor, Sensor.TYPE_TEMPERATURE, DEFAULT_UPDATE_TIME));
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Verifies if a specific sensor is supported on the handheld device.
     * @param sensorType is used to access the specific sensor mentioned above.
     * @return Boolean if it is supported or not.
     */
    public boolean isSupported(final int sensorType) {
        return mAvailableSensors.get(sensorType);
    }

    //TODO
    public void setUpdateTime(final int sesnorType, final int updateTime) {
        //Criar na posicao myListeners(sensorType)
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
    private boolean registerSensor(final PervasiveSensorAdapter listener, final int sensorType,
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
     * Unregister all sensors from the SensorManager.
     */
    public void unregisterAllSensors() {
        for (int i = 0; i < myListeners.size(); i++) {
            mSensorManager.unregisterListener(this,
                    mSensorManager.getDefaultSensor(myListeners.get(i).getSensorType()));
        }
        mAvailableSensors.clear();
        myListeners.clear();
    }

    /**
     * Calls the onSensorChanged of each sensor, when new information come.
     *
     * @param sensorValues
     * @param sensorType
     */
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
