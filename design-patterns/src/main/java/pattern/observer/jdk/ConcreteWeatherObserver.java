package pattern.observer.jdk;

import java.util.Observable;
import java.util.Observer;

// 观察者
public class ConcreteWeatherObserver implements Observer {

    // 观察者名称的变量
    private String observerName;

    @Override
    public void update(Observable o, Object arg) {
        // 第一种推的方式
        System.out.println(observerName + " 推模式获取天气信息：" + arg);
        // 第二种拉的模式
        System.out.println(observerName + " 拉模式, 拉取天气信息: " +
                ((ConcreteWeatherSubject)o).getContent());
    }

    public String getObserverName() {
        return observerName;
    }

    public void setObserverName(String observerName) {
        this.observerName = observerName;
    }
}
