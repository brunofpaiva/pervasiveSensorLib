package development.master.com.pervasivesensorlib.sensors;

import android.hardware.Sensor;
import android.widget.TextView;

import development.master.com.pervasivesensorlib.PervasiveSensorAdapter;
import development.master.com.pervasivesensorlib.SensorCallback;

public class RelativeHumiditySensor extends PervasiveSensorAdapter {

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

    public RelativeHumiditySensor(final TextView textView) {
        mTextView = textView;
    }

    public RelativeHumiditySensor(final SensorCallback callback) {
        mCallback = callback;
    }

    @Override
    public int getSensorType() {
        return Sensor.TYPE_RELATIVE_HUMIDITY;
    }

    @Override
    public String getCurrentValues() {
        return mCurrentValues;
    }

    @Override
    public void OnLinearAccelerationSensorChanged(final float[] sensorValues) {
        final float relativeHumidity = sensorValues[0];

        mCurrentValues = ("Relative Humidity: " + relativeHumidity);
        if (mCallback != null) {
            mCallback.onReceiveRelativeHumidityData(relativeHumidity);
        }
    }
}
