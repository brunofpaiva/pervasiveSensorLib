package development.master.com.pervasivesensorlib.sensors;

import android.hardware.Sensor;
import android.widget.TextView;

import development.master.com.pervasivesensorlib.PervasiveSensorAdapter;
import development.master.com.pervasivesensorlib.SensorCallback;

public class ProximitySensor extends PervasiveSensorAdapter {

    /**
     * String used to return the current value from sensor.
     */
    private String mCurrentValues;

    /**
     * The callback used to retrieve data from Proximity sensor.
     */
    private SensorCallback mCallback;

    public ProximitySensor(final SensorCallback callback) {
        mCallback = callback;
    }

    public void setCallback(final SensorCallback callback) {
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
    public void OnProximitySensorChanged(final float[] sensorValues) {
        final float distance = sensorValues[0];
        if (mCallback != null) {
            mCallback.onReceiveProximityData(distance == 0);
        }

        if (distance == 0) {
            mCurrentValues = "Proximity sensor is Near";
        } else {
            mCurrentValues = "Proximity sensor is Far";
        }
    }
}
