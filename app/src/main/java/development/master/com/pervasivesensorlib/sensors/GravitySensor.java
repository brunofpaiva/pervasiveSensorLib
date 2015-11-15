package development.master.com.pervasivesensorlib.sensors;

import android.hardware.Sensor;
import android.widget.TextView;

import development.master.com.pervasivesensorlib.PervasiveSensorAdapter;
import development.master.com.pervasivesensorlib.SensorCallback;

public class GravitySensor extends PervasiveSensorAdapter{

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

    public GravitySensor(final TextView textVeiw) {
        mTextView = textVeiw;
    }

    public GravitySensor(final SensorCallback callback) {
        mCallback = callback;
    }

    @Override
    public int getSensorType() {
        return Sensor.TYPE_GRAVITY;
    }

    @Override
    public String getCurrentValues() {
        return mCurrentValues;
    }

    @Override
    public void OnGravitySensorChanged(final float[] sensorValues) {
        final float x = sensorValues[0];
        final float y = sensorValues[1];
        final float z = sensorValues[2];

        mCurrentValues = ("X: " + sensorValues[0] + "\n"
                + "Y: " + sensorValues[1] + "\n"
                + "Z: " + sensorValues[2]);
        if (mCallback != null) {
            mCallback.onReceiveGravityData(x, y, z);
        }
    }
}
