package development.master.com.pervasivesensorlib.sensors;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.widget.TextView;

import java.lang.reflect.UndeclaredThrowableException;

import development.master.com.pervasivesensorlib.PervasiveSensorAdapter;
import development.master.com.pervasivesensorlib.PervasiveSensorManager;
import development.master.com.pervasivesensorlib.SensorCallback;

public class AccelerometerSensor extends PervasiveSensorAdapter {

    /**
     * String used to return the current value from sensor.
     */
    private String mCurrentValues;

    /**
     * The callback used to retrieve data from Accelerometer sensor.
     */
    private SensorCallback mCallback;

    /**
     * Constructor that receives the SensorCallback which will return the sensor value.
     * @param callback
     */
    public AccelerometerSensor(final SensorCallback callback) {
        mCallback = callback;
    }

    @Override
    public int getSensorType() {
        return Sensor.TYPE_ACCELEROMETER;
    }

    @Override
    public String getCurrentValues() {
        return mCurrentValues;
    }

    public void setCallback(final SensorCallback callback) {
        mCallback = callback;
    }

    @Override
    public void OnAccelerometerSensorChanged(final float[] sensorValues) {
        final float x = sensorValues[0];
        final float y = sensorValues[1];
        final float z = sensorValues[2];

        mCurrentValues = ("X: " + x + "\n" + "Y: " + y + "\n" + "Z: " + z);
        if (null != mCallback) {
            mCallback.onReceiveAccelerometerData(x,y,z);
        }
    }

    public boolean hadShaken() {
        return false;
    }
}
