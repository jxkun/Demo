package pattern.observer.temp;

/**
 * 具体观察者对象， 实现更新的方法， 使自身的状态和目标的状态保持一致
 */
public class ConcreteObserver implements Observer {
    private String observerName;

    // 目标对象的状态
    private String observerState;

    // 拉模式实现的观察者, 将subject换成目标对象的状态, 则为推模式的观察者, 调用了update的方法要相应的进行修改
    @Override
    public void update(Subject subject) {
        this.observerState = ((ConcreteSubject) subject).getSubjectState();
        System.out.println(observerName + " 拉取的内容：" + observerState);
    }

    public String getObserverName() {
        return observerName;
    }

    public void setObserverName(String observerName) {
        this.observerName = observerName;
    }
}
