package development.master.com.pervasivesensorlib;

public abstract class PervasiveSensorAdapter implements PervasiveSensorListener {

    public abstract int getSensorType();

    public abstract String getCurrentValues();

    @Override
    public void OnAccelerometerSensorChanged(float[] sensorValues) {
    }

    @Override
    public void OnAmbientTemperatureSensorChanged(float[] sensorValues) {
    }

    @Override
    public void OnGravitySensorChanged(float[] sensorValues) {
    }

    @Override
    public void OnGyroscopeSensorChanged(float[] sensorValues) {
    }

    @Override
    public void OnLightSensorChanged(float[] sensorValues) {
    }

    @Override
    public void OnLinearAccelerationSensorChanged(float[] sensorValues) {
    }

    @Override
    public void OnMagneticFieldSensorChanged(float[] sensorValues) {
    }

    @Override
    public void OnOrientationSensorChanged(float[] sensorValues) {
    }

    @Override
    public void OnPressureSensorChanged(float[] sensorValues) {
    }

    @Override
    public void OnProximitySensorChanged(float[] sensorValues) {
    }

    @Override
    public void OnRelativeHumiditySensorChanged(float[] sensorValues) {
    }

    @Override
    public void OnRotationVectorSensorChanged(float[] sensorValues) {
    }

    @Override
    public void OnTemperatureSensorChanged(float[] sensorValues) {
    }
}
