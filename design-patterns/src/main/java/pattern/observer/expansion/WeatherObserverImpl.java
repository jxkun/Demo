package pattern.observer.expansion;

public class WeatherObserverImpl implements WeatherObserver {
    private String observerName;

    private String weatherContent;

    @Override
    public void update(WeatherSubject subject) {
        this.weatherContent = ((ConWeatherSubject)subject).getWeatherContent();
        System.out.println(observerName + " 拉取天气信息: " + weatherContent);
    }

    @Override
    public void setObserverName(String observerName) {
        this.observerName = observerName;
    }

    @Override
    public String getObserverName() {
        return this.observerName;
    }
}
