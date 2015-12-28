package development.master.com.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import development.master.com.pervasivesensorlib.PervasiveSensorManager;
import development.master.com.pervasivesensorlib.SensorCallback;
import development.master.com.pervasivesensorlib.SensorCallbackAdapter;
import development.master.com.pervasivesensorlib.sensors.AccelerometerSensor;
import development.master.com.pervasivesensorlib.sensors.LightSensor;

public class MainActivity extends AppCompatActivity {

    private PervasiveSensorManager psManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView text = (TextView) findViewById(R.id.text);
        final TextView text2 = (TextView) findViewById(R.id.text2);

        psManager = new PervasiveSensorManager(this);

        SensorCallback sensorCallback = new SensorCallbackAdapter() {
            @Override
            public void onReceiveAccelerometerData(float x, float y, float z) {
                text.setText("Acceleration X: " + x + "\nAcceleration Y: " + y + "\nAcceleration Z: " + z);
            }

            @Override
            public void onReceiveLightData(float light) {
                text2.setText(""+light);
            }
        };

        new LightSensor(psManager, sensorCallback);
        new AccelerometerSensor(psManager, sensorCallback);

    }
}
