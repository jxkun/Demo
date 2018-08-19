package pattern.observer.jdk;

public class WeatcherClient {
    public static void main(String[] args) {
        ConcreteWeatherSubject subject = new ConcreteWeatherSubject();

        ConcreteWeatherObserver observerA = new ConcreteWeatherObserver();
        observerA.setObserverName("observerA");
        ConcreteWeatherObserver observerB = new ConcreteWeatherObserver();
        observerB.setObserverName("observerB");

        subject.addObserver(observerA);
        subject.addObserver(observerB);
        subject.setContent("今天天气晴朗, 气温28度, 适合出行");
    }
}
