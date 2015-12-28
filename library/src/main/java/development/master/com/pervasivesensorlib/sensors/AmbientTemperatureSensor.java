package development.master.com.pervasivesensorlib.sensors;

import android.hardware.Sensor;
import android.widget.TextView;

import development.master.com.pervasivesensorlib.PervasiveSensorAdapter;
import development.master.com.pervasivesensorlib.SensorCallback;

public class AmbientTemperatureSensor extends PervasiveSensorAdapter {

    /**
     * String used to return the current value from sensor.
     */
    private String mCurrentValues;

    /**
     * The callback used to retrieve data from Ambient Temperature sensor.
     */
    private SensorCallback mCallback;

    /**
     * Default Constructor.
     */
    public AmbientTemperatureSensor() {
    }

    public AmbientTemperatureSensor(final SensorCallback callback) {
        mCallback = callback;
    }

    @Override
    public int getSensorType() {
        return Sensor.TYPE_LIGHT;
    }

    @Override
    public String getCurrentValues() {
        return mCurrentValues;
    }

    @Override
    public void OnAmbientTemperatureSensorChanged(final float[] sensorValues) {
        final float temperatureValue = sensorValues[0];
        mCurrentValues = "Ambient Temperature = " + temperatureValue;
        if (mCallback != null) {
            mCallback.onReceiveAmbientTemperatureData(temperatureValue);
        }
    }

}
