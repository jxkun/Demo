package pattern.observer.expansion;

import java.util.ArrayList;
import java.util.List;

// 定义抽象的观察目标
public abstract class WeatherSubject {
    // 用来保存注册的观察者
    protected List<WeatherObserver> observers = new ArrayList<>();

    // attach detach notifyObservers
    // 吧订阅天气的人、观察者添加到订阅者列表
    public void attach(WeatherObserver observer){
        observers.add(observer);
    }

    // 删除集合中指定的订阅者
    public void detach(WeatherObserver observer){
        observers.remove(observer);
    }

    protected abstract void notifyObservers();


}
