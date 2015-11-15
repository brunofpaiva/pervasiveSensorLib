package development.master.com.pervasivesensorlib;

public interface SensorCallback {

    void onReceiveAccelerometerData(final float x, final float y, final float z);
    void onReceiveAmbientTemperatureData(final float temperature);
    void onReceiveGravityData(final float x, final float y, final float z);
    void onReceiveGyroscopeData(final float x, final float y, final float z);
    void onReceiveLightData(final float light);
    void onReceiveLinearAccelereationData(final float x, final float y, final float z);
    void onReceiveMagneticFieldData(final float magneticFieldValue);
    void onReceiveOrientationData(final float orientation);
    void onReceivePressureData(final float pressure);
    void onReceiveProximityData(final boolean isNear);
    void onReceiveRelativeHumidityData(final float relativeHumidity);
    void onReceiveRotationVectorData(final float x, final float y, final float z);
    void onReceiveTemperatureData(final float deviceTemperature);

}
