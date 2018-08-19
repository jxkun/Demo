package pattern.observer.temp;

/**
 * 具体的目标对象, 负责把有关正太存入到相应的观察者对象中
 */
public class ConcreteSubject extends Subject{
    // 目标对象的状态
    private String subjectState;

    public String getSubjectState() {
        return subjectState;
    }

    // 目标对象状态改变, 通知所有观察者
    public void setSubjectState(String subjectState) {
        this.subjectState = subjectState;
        this.notifyObservers();
    }
}
