package development.master.com.pervasivesensorlib.sensors;

import android.hardware.Sensor;
import android.widget.TextView;

import development.master.com.pervasivesensorlib.PervasiveSensorAdapter;
import development.master.com.pervasivesensorlib.SensorCallback;

public class RotationVectorSensor extends PervasiveSensorAdapter {

    /**
     * String used to return the current value from sensor.
     */
    private String mCurrentValues;

    /**
     * The callback used to retrieve data from Rotation Vector sensor.
     */
    private SensorCallback mCallback;

    /**
     * Default Constructor.
     */
    public RotationVectorSensor() {
    }

    public RotationVectorSensor(final SensorCallback callback) {
        mCallback = callback;
    }

    @Override
    public int getSensorType() {
        return Sensor.TYPE_ROTATION_VECTOR;
    }

    @Override
    public String getCurrentValues() {
        return mCurrentValues;
    }

    @Override
    public void OnRotationVectorSensorChanged(final float[] sensorValues) {
        final float x = sensorValues[0];
        final float y = sensorValues[1];
        final float z = sensorValues[2];

        mCurrentValues = ("X: " + x + "\n" + "Y: " + y + "\n" + "Z: " + z);

        if (mCallback != null) {
            mCallback.onReceiveRotationVectorData(x,y,z);
        }
    }
}
