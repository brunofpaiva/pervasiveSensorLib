package development.master.com.pervasivesensorlib.sensors;

import android.hardware.Sensor;
import android.widget.TextView;

import development.master.com.pervasivesensorlib.PervasiveSensorAdapter;
import development.master.com.pervasivesensorlib.SensorCallback;

public class OrientationSensor extends PervasiveSensorAdapter {

    /**
     * String used to return the current value from sensor.
     */
    private String mCurrentValues;

    /**
     * The callback used to retrieve data from Orientation sensor.
     */
    private SensorCallback mCallback;

    public OrientationSensor(final SensorCallback callback) {
        mCallback = callback;
    }

    public void setCallback(final SensorCallback callback) {
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
