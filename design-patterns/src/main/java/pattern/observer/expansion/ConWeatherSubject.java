package pattern.observer.expansion;

import java.util.List;

public class ConWeatherSubject extends WeatherSubject {

    // 目标对象的状态, 下雨, 下雪, 晴天
    private String weatherContent;

    @Override
    protected void notifyObservers() {
        List<WeatherObserver> observers = this.observers;
        for(WeatherObserver weatherObserver : observers){
            String observerName = weatherObserver.getObserverName();
            if("下雨".equals(weatherContent) && (observerName.equals("observerA") || observerName.equals("observerB"))){
                weatherObserver.update(this);
            }else if("下雪".equals(weatherContent) && observerName.equals("observerB")){
                weatherObserver.update(this);
            }else if(!"observerA".equals(observerName) && !"observerB".equals(observerName)){
                weatherObserver.update(this);
            }
        }
    }

    public String getWeatherContent() {
        return weatherContent;
    }

    public void setWeatherContent(String weatherContent) {
        this.weatherContent = weatherContent;
        this.notifyObservers();
    }
}
