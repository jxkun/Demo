package pattern.observer.jdk;

import java.util.Observable;

// 天气目标的具体实现类
public class ConcreteWeatherSubject extends Observable {

    // 天气情况的内容
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        // 天气情况有了, 就要通知所有的观察者
        // 再通知之前, 在用Java的Observer模式时, 下面这句话不可少
        this.setChanged();

        // 主动通知, 推模型
        this.notifyObservers(content);

        // 拉模型, 则使用:
        // this.notifyObservers();
    }
}
