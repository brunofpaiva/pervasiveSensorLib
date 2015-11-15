package development.master.com.pervasivesensorlib.sensors;

import android.hardware.Sensor;
import android.widget.TextView;

import development.master.com.pervasivesensorlib.PervasiveSensorAdapter;
import development.master.com.pervasivesensorlib.SensorCallback;

public class ProximitySensor extends PervasiveSensorAdapter {

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

    public ProximitySensor(final TextView textView) {
        mTextView = textView;
    }

    public ProximitySensor(final SensorCallback callback) {
        mCallback = callback;
    }

    @Override
    public int getSensorType() {
        return Sensor.TYPE_PROXIMITY;
    }

    @Override
    public String getCurrentValues() {
        return null;
    }

    @Override
    public void OnProximitySensorChanged(float[] sensorValues) {
        final float distance = sensorValues[0];
        if (mCallback != null) {
            mCallback.onReceiveProximityData(distance == 0);
        }

        if (distance == 0) {
            mCurrentValues = "Proximity sensor is Near";
        } else {
            mCurrentValues = "Proximity sensor is Far";
        }

        if (mTextView != null) {
            mTextView.setText(mCurrentValues);
        }
    }
}
