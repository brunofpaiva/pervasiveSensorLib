package development.master.com.pervasivesensorlib.sensors;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.widget.TextView;

import development.master.com.pervasivesensorlib.PervasiveSensorAdapter;
import development.master.com.pervasivesensorlib.PervasiveSensorManager;
import development.master.com.pervasivesensorlib.SensorCallback;

public class LightSensor extends PervasiveSensorAdapter {

    /**
     * String used to return the current value from sensor.
     */
    private String mCurrentValues;

    /**
     * The callback used to retrieve data from Light sensor.
     */
    private SensorCallback mCallback;

    /**
     * Default Constructor.
     */
    public LightSensor(final PervasiveSensorManager psManager) {
        if (null != psManager) {
            psManager.registerSensor(this, Sensor.TYPE_LIGHT, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    public LightSensor(final PervasiveSensorManager psManager, final SensorCallback callback) {
        if (null != psManager) {
            psManager.registerSensor(this, Sensor.TYPE_LIGHT, SensorManager.SENSOR_DELAY_NORMAL);
            mCallback = callback;
        }
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
    public void OnLightSensorChanged(final float[] sensorValues) {
        final float lightValue = sensorValues[0];
        mCurrentValues = "Ilumination = " + lightValue;
        if (mCallback != null) {
            mCallback.onReceiveLightData(lightValue);
        }
    }

}
