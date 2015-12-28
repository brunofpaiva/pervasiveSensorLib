package development.master.com.pervasivesensorlib.sensors;

import android.hardware.Sensor;
import android.widget.TextView;

import development.master.com.pervasivesensorlib.PervasiveSensorAdapter;
import development.master.com.pervasivesensorlib.SensorCallback;

public class RelativeHumiditySensor extends PervasiveSensorAdapter {

    /**
     * String used to return the current value from sensor.
     */
    private String mCurrentValues;

    /**
     * The callback used to retrieve data from Relative Humidity sensor.
     */
    private SensorCallback mCallback;

    /**
     * Default Constructor.
     */
    public RelativeHumiditySensor() {
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
