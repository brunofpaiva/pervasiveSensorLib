package development.master.com.pervasivesensorlib.sensors;

import android.hardware.Sensor;
import android.widget.TextView;

import development.master.com.pervasivesensorlib.PervasiveSensorAdapter;
import development.master.com.pervasivesensorlib.SensorCallback;

public class OrientationSensor extends PervasiveSensorAdapter {

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

    public OrientationSensor(final TextView textView) {
        mTextView = textView;
    }

    public OrientationSensor(final SensorCallback callback) {
        mCallback = callback;
    }

    @Override
    public int getSensorType() {
        return Sensor.TYPE_ORIENTATION;
    }

    @Override
    public String getCurrentValues() {
        return mCurrentValues;
    }

    @Override
    public void OnLinearAccelerationSensorChanged(final float[] sensorValues) {
        final float orientation = sensorValues[0];

        mCurrentValues = ("Orientation: " + orientation);
        if (mCallback != null) {
            mCallback.onReceiveOrientationData(orientation);
        }
    }
}
