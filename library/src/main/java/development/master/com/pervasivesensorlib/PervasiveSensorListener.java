package development.master.com.pervasivesensorlib;

public interface PervasiveSensorListener {

    void OnAccelerometerSensorChanged(final float[] sensorValues);
    void OnAmbientTemperatureSensorChanged(final float[] sensorValues);
    void OnGravitySensorChanged(final float[] sensorValues);
    void OnGyroscopeSensorChanged(final float[] sensorValues);
    void OnLightSensorChanged(final float[] sensorValues);
    void OnLinearAccelerationSensorChanged(final float[] sensorValues);
    void OnMagneticFieldSensorChanged(final float[] sensorValues);
    void OnOrientationSensorChanged(final float[] sensorValues);
    void OnPressureSensorChanged(final float[] sensorValues);
    void OnProximitySensorChanged(final float[] sensorValues);
    void OnRelativeHumiditySensorChanged(final float[] sensorValues);
    void OnRotationVectorSensorChanged(final float[] sensorValues);
    void OnTemperatureSensorChanged(final float[] sensorValues);
}
