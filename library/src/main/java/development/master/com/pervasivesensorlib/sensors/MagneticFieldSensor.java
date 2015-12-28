package development.master.com.pervasivesensorlib.sensors;

import android.hardware.Sensor;
import android.widget.TextView;

import development.master.com.pervasivesensorlib.PervasiveSensorAdapter;
import development.master.com.pervasivesensorlib.SensorCallback;

public class MagneticFieldSensor extends PervasiveSensorAdapter {

    /**
     * String used to return the current value from sensor.
     */
    private String mCurrentValues;

    /**
     * The callback used to retrieve data from Magnetic Field sensor.
     */
    private SensorCallback mCallback;

    /**
     * Default Constructor.
     */
    public MagneticFieldSensor() {
    }

    public MagneticFieldSensor(final SensorCallback callback) {
        mCallback = callback;
    }

    @Override
    public int getSensorType() {
        return Sensor.TYPE_MAGNETIC_FIELD;
    }

    @Override
    public String getCurrentValues() {
        return mCurrentValues;
    }

    @Override
    public void OnMagneticFieldSensorChanged(final float[] sensorValues) {
        final float magneticField = sensorValues[0];

        mCurrentValues = ("Magnetic Field: " + magneticField);
        if (mCallback != null) {
            mCallback.onReceiveMagneticFieldData(magneticField);
        }
    }
}
