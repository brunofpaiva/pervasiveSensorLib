package development.master.com.pervasivesensorlib.sensors;

import android.hardware.Sensor;
import android.widget.TextView;

import development.master.com.pervasivesensorlib.PervasiveSensorAdapter;
import development.master.com.pervasivesensorlib.SensorCallback;

public class TemperatureSensor extends PervasiveSensorAdapter {

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

    public TemperatureSensor(final TextView textView) {
        mTextView = textView;
    }

    public TemperatureSensor(final SensorCallback callback) {
        mCallback = callback;
    }

    @Override
    public int getSensorType() {
        return Sensor.TYPE_TEMPERATURE;
    }

    @Override
    public String getCurrentValues() {
        return mCurrentValues;
    }

    @Override
    public void OnRotationVectorSensorChanged(final float[] sensorValues) {
        final float temperature = sensorValues[0];

        mCurrentValues = ("Device Temperature: " + temperature);

        if (mCallback != null) {
            mCallback.onReceiveTemperatureData(temperature);
        }
    }
}
