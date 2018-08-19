package pattern.observer.expansion;

/**
 * 需求：
 * observerA: 只订阅下雨天
 * observerB: 只订阅下雨天和下雪天
 * 其他人， 订阅所有天气
 */
public class WeatherClient2 {

    public static void main(String[] args) {
        ConWeatherSubject subject = new ConWeatherSubject();

        WeatherObserverImpl observerA = new WeatherObserverImpl();
        observerA.setObserverName("observerA");
        WeatherObserverImpl observerB = new WeatherObserverImpl();
        observerB.setObserverName("observerB");
        WeatherObserverImpl observerC = new WeatherObserverImpl();
        observerC.setObserverName("observerC");

        subject.attach(observerA);
        subject.attach(observerB);
        subject.attach(observerC);
        subject.setWeatherContent("下雨");
        subject.setWeatherContent("下雪");
        subject.setWeatherContent("天晴");
    }
}
