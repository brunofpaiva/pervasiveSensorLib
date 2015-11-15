package development.master.com.pervasivesensorlib;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import development.master.com.pervasivesensorlib.sensors.AccelerometerSensor;
import development.master.com.pervasivesensorlib.sensors.AmbientTemperatureSensor;
import development.master.com.pervasivesensorlib.sensors.LightSensor;
import development.master.com.pervasivesensorlib.sensors.ProximitySensor;

public class MainActivity extends Activity {

    private PervasiveSensorManager psm;

    private AccelerometerSensor mAccelerometerSensor;
    private AmbientTemperatureSensor mAmbientTempSensor;
    private LightSensor mLightSensor;
    private ProximitySensor mProximitySensor;

    // Accelerometer variables
    private float last_x, last_y, last_z;
    private long lastUpdate = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tvAccelerometer = (TextView) findViewById(R.id.text_accelerometer);
        final TextView tvLight = (TextView) findViewById(R.id.text_light);
        final TextView tvProximity  = (TextView) findViewById(R.id.text_proximity);
        final TextView tvAmbient = (TextView) findViewById(R.id.text_ambient_temp);

        psm = new PervasiveSensorManager(this);

        mAccelerometerSensor = new AccelerometerSensor(accelerometerSensorLogic(tvAccelerometer));
        mAmbientTempSensor = new AmbientTemperatureSensor(tvAmbient);
        mLightSensor = new LightSensor(lightSensorLogic(tvLight));
        mProximitySensor = new ProximitySensor(proximitySensorLogic(tvProximity));

        if (psm.registerSensor(mAccelerometerSensor, Sensor.TYPE_ACCELEROMETER, SensorManager.SENSOR_DELAY_NORMAL)) {
            tvAccelerometer.setText("AccelerometerSensor is not supported on this Device!");
        }

        if (psm.registerSensor(mLightSensor, Sensor.TYPE_LIGHT, SensorManager.SENSOR_DELAY_NORMAL)) {
            tvAccelerometer.setText("LightSensor is not supported on this Device!");
        }

        if(psm.registerSensor(mProximitySensor, Sensor.TYPE_PROXIMITY, SensorManager.SENSOR_DELAY_NORMAL)) {
            tvAccelerometer.setText("ProximitySensor is not supported on this Device!");
        }

        if (!psm.registerSensor(mAmbientTempSensor, Sensor.TYPE_AMBIENT_TEMPERATURE, SensorManager.SENSOR_DELAY_NORMAL)) {
            tvAmbient.setText("AmbientTemperatureSensor is not supported on this Device!");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        psm.unregisterAllSensors();
    }

    private SensorCallback accelerometerSensorLogic(final TextView tvAccelerometer) {
        return new SensorCallbackAdapter() {
            @Override
            public void onReceiveAccelerometerData(float x, float y, float z) {
                final long curTime = System.currentTimeMillis();
                if ((curTime - lastUpdate) > 100) {
                    tvAccelerometer.setText("X: " + x + "\n" + "Y: " + y + "\n" + "Z: " + z);
                    long diffTime = (curTime - lastUpdate);
                    lastUpdate = curTime;

                    float speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;

                    if (speed > 900) {
                        Toast.makeText(getBaseContext(), "Shaked!!!", Toast.LENGTH_SHORT).show();
                    }

                    last_x = x;
                    last_y = y;
                    last_z = z;
                }
            }
        };
    }

    private SensorCallback lightSensorLogic(final TextView tvLight) {
        return new SensorCallbackAdapter() {
            @Override
            public void onReceiveLightData(final float light) {
                tvLight.setText("Ilumination = " + light);
            }
        };
    }

    private SensorCallback proximitySensorLogic(final TextView tvProximity) {
        return new SensorCallbackAdapter() {
            @Override
            public void onReceiveProximityData(final boolean isNear) {
                if (isNear) {
                    tvProximity.setText("NEAR");
                } else {
                    tvProximity.setText("FAR");
                }
            }
        };
    }
}
