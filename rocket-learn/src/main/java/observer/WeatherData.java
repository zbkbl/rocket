package observer;

import java.util.ArrayList;
import java.util.Observable;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-12-11 18:17
 **/
public class WeatherData extends Observable {

    private float temperature;

    private float humidity;

    private float pressure;

    public WeatherData() {
    }


    public void measurementsChanged() {
        setChanged();
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }
}
