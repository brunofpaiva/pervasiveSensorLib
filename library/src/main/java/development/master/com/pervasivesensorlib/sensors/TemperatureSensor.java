package development.master.com.pervasivesensorlib.sensors;

import android.hardware.Sensor;
import android.widget.TextView;

import development.master.com.pervasivesensorlib.PervasiveSensorAdapter;
import development.master.com.pervasivesensorlib.SensorCallback;

public class TemperatureSensor extends PervasiveSensorAdapter {

    /**
     * String used to return the current value from sensor.
     */
    private String mCurrentValues;

    /**
     * The callback used to retrieve data from Temperature sensor.
     */
    private SensorCallback mCallback;

    /**
     * Default Constructor.
     */
    public TemperatureSensor() {
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
