package observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-12-11 18:22
 **/
public class CurrentConditionsDisplay implements Observer, DisplayElement {

    private float temperature;

    private float humidity;

    private Observable weatherData;


    public CurrentConditionsDisplay(Observable weatherData){
        this.weatherData = weatherData;
        weatherData.addObserver(this);
    }

    @Override
    public void display() {
        System.out.println("Current conditions: " + temperature + "F degrees and " +
                humidity + "% humidity");
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof WeatherData){
            WeatherData w = (WeatherData) o;
            this.temperature = w.getTemperature();
            this.humidity = w.getHumidity();
            display();
        }
    }

    void cancleRegister(){
        this.weatherData.deleteObserver(this);
    }

    void register(){
        this.weatherData.addObserver(this);
    }
}
