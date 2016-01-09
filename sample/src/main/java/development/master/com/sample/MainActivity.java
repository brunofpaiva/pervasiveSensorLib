package development.master.com.sample;

import android.hardware.Sensor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;

import development.master.com.pervasivesensorlib.PervasiveSensorManager;
import development.master.com.pervasivesensorlib.SensorCallback;
import development.master.com.pervasivesensorlib.SensorCallbackAdapter;

public class MainActivity extends AppCompatActivity {

    private PervasiveSensorManager psManager;
    private TextView text, text2, text3, text4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.text);
        text2 = (TextView) findViewById(R.id.text2);
        text3 = (TextView) findViewById(R.id.text3);
        text4 = (TextView) findViewById(R.id.text4);

        final SensorCallback sensorCallback = createSensorCallback();

        final ArrayList<Integer> sensors = new ArrayList<>();
        sensors.add(Sensor.TYPE_ACCELEROMETER);
        sensors.add(Sensor.TYPE_AMBIENT_TEMPERATURE);
        sensors.add(Sensor.TYPE_LIGHT);
        sensors.add(Sensor.TYPE_PROXIMITY);

        psManager = new PervasiveSensorManager(this, sensorCallback, sensors);

        if (!psManager.isSupported(Sensor.TYPE_ACCELEROMETER)) {
            text.setText("Accelerometer não é suportado!");
        }

        if (!psManager.isSupported(Sensor.TYPE_AMBIENT_TEMPERATURE)) {
            text2.setText("AmbientTemperature não é suportado!");
        }

        if (!psManager.isSupported(Sensor.TYPE_LIGHT)) {
            text3.setText("Light não é suportado!");
        }

//TODO
//        psManager.setUpdateTime(sensors.get(0), 2.0);

    }

    private SensorCallback createSensorCallback() {
        return new SensorCallbackAdapter() {
            @Override
            public void onReceiveAccelerometerData(float x, float y, float z) {
                text.setText("Acceleration X: " + x + "\nAcceleration Y: " + y + "\nAcceleration Z: " + z);
            }

            @Override
            public void onReceiveAmbientTemperatureData(float temperature) {
                text2.setText("Acceleration X: " + temperature);
            }

            @Override
            public void onReceiveLightData(float light) {
                text3.setText(light + " units of light");
            }

            @Override
            public void onReceiveProximityData(boolean isNear) {
                text4.setText("Is Near? " + isNear);
            }
        };
    }
}
