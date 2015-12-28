package development.master.com.sample;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import development.master.com.pervasivesensorlib.PervasiveSensorManager;
import development.master.com.pervasivesensorlib.SensorCallback;
import development.master.com.pervasivesensorlib.SensorCallbackAdapter;
import development.master.com.pervasivesensorlib.sensors.AccelerometerSensor;

public class MainActivity extends AppCompatActivity {

    private PervasiveSensorManager psManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView text = (TextView) findViewById(R.id.text);

        psManager = new PervasiveSensorManager(this);

        SensorCallback sensorCallback = new SensorCallbackAdapter() {
            @Override
            public void onReceiveAccelerometerData(float x, float y, float z) {
                text.setText("Acceleration X: " + x + "\nAcceleration Y: " + y + "\nAcceleration Z: " + z);
            }
        };

        AccelerometerSensor accelerometer = new AccelerometerSensor(sensorCallback);
        psManager.registerSensor(accelerometer, Sensor.TYPE_ACCELEROMETER,
                SensorManager.SENSOR_DELAY_NORMAL);

    }
}
