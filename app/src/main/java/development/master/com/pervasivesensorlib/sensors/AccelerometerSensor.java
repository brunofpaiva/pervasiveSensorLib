package development.master.com.pervasivesensorlib.sensors;

import android.hardware.Sensor;
import android.widget.TextView;

import development.master.com.pervasivesensorlib.PervasiveSensorAdapter;
import development.master.com.pervasivesensorlib.SensorCallback;

public class AccelerometerSensor extends PervasiveSensorAdapter {

    private String mCurrentValues;

    /**
     * TextView value where the received sensor data will be updated to user.
     */
    private TextView mTextValue;

    private SensorCallback mCallback;

    public AccelerometerSensor(final TextView textView) {
        mTextValue = textView;
    }

    public AccelerometerSensor(final SensorCallback callback) {
        mCallback = callback;
    }

    @Override
    public int getSensorType() {
        return Sensor.TYPE_ACCELEROMETER;
    }

    @Override
    public String getCurrentValues() {
        return mCurrentValues;
    }

    @Override
    public void OnAccelerometerSensorChanged(final float[] sensorValues) {
        final float x = sensorValues[0];
        final float y = sensorValues[1];
        final float z = sensorValues[2];

        mCurrentValues = ("X: " + sensorValues[0] + "\n"
                        + "Y: " + sensorValues[1] + "\n"
                        + "Z: " + sensorValues[2]);
        if (mCallback != null) {
            mCallback.onReceiveAccelerometerData(x,y,z);
        }
    }
}
