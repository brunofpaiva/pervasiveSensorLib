package development.master.com.pervasivesensorlib.sensors;

import android.hardware.Sensor;
import android.widget.TextView;

import development.master.com.pervasivesensorlib.PervasiveSensorAdapter;
import development.master.com.pervasivesensorlib.SensorCallback;

public class MagneticFieldSensor extends PervasiveSensorAdapter {

    /**
     * This variable keeps the last value retrieved from sensor.
     */
    private String mCurrentValues;

    /**
     * TextView that updates the received sensor data to.
     */
    private TextView mTextView;

    /**
     * Its a callback used to receive the sensor value at main activity.
     */
    private SensorCallback mCallback;

    public MagneticFieldSensor(final TextView textView) {
        mTextView = textView;
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
