package development.master.com.pervasivesensorlib.sensors;

import android.hardware.Sensor;
import android.widget.TextView;

import development.master.com.pervasivesensorlib.PervasiveSensorAdapter;
import development.master.com.pervasivesensorlib.SensorCallback;

public class PressureSensor extends PervasiveSensorAdapter {

    /**
     * String used to return the current value from sensor.
     */
    private String mCurrentValues;

    /**
     * The callback used to retrieve data from Pressure sensor.
     */
    private SensorCallback mCallback;

    /**
     * Default Constructor.
     */
    public PressureSensor() {
    }

    public PressureSensor(final SensorCallback callback) {
        mCallback = callback;
    }

    @Override
    public int getSensorType() {
        return Sensor.TYPE_PRESSURE;
    }

    @Override
    public String getCurrentValues() {
        return mCurrentValues;
    }

    @Override
    public void OnLinearAccelerationSensorChanged(final float[] sensorValues) {
        final float pressure = sensorValues[0];

        mCurrentValues = ("Pressure: " + pressure);
        if (mCallback != null) {
            mCallback.onReceivePressureData(pressure);
        }
    }
}
