package observer;


/**
 * @description:
 * @author: Liuyang
 * @date: 2019-12-11 18:28
 **/
public class WeatherStation {

    public static void main(String[] args) {
        WeatherData w = new WeatherData();
        CurrentConditionsDisplay  c = new CurrentConditionsDisplay(w);

        w.setMeasurements(60,85,30.4f);
        c.cancleRegister();
        w.setMeasurements(50,20,33.4f);
        c.register();
        w.setMeasurements(30,40,22.5f);

    }
}
