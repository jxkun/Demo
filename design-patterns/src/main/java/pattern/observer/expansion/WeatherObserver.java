package pattern.observer.expansion;

// 定义一个更新的接口方法给那些在目标发生改变的时候被通知的观察者对象调用
public interface WeatherObserver {

    // 更新接口
    void update(WeatherSubject subject);
    // 设置观察者名称
    void setObserverName(String observerName);
    // 获取观察者名称
    String getObserverName();
}
