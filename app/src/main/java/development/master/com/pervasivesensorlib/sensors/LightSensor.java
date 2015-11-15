package development.master.com.pervasivesensorlib.sensors;

import android.hardware.Sensor;
import android.widget.TextView;

import development.master.com.pervasivesensorlib.PervasiveSensorAdapter;
import development.master.com.pervasivesensorlib.SensorCallback;

public class LightSensor extends PervasiveSensorAdapter {

    private String mCurrentValues;

    private SensorCallback mCallback;

    /**
     * TextView value where the received sensor data will be updated to user.
     */
    private TextView mTextView;

    public LightSensor(final TextView textView) {
        mTextView = textView;
    }

    public LightSensor(final SensorCallback callback) {
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
    public void OnLightSensorChanged(final float[] sensorValues) {
        final float lightValue = sensorValues[0];
        mCurrentValues = "Ilumination = " + lightValue;
        if (mTextView != null) {
            mTextView.setText(mCurrentValues);
        }
        if (mCallback != null) {
            mCallback.onReceiveLightData(lightValue);
        }
    }

}
