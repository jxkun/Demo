package pattern.observer.temp;

public class Client {

    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();

        ConcreteObserver observer1 = new ConcreteObserver();
        observer1.setObserverName("observer1");
        ConcreteObserver observer2 = new ConcreteObserver();
        observer2.setObserverName("observer2");

        subject.attach(observer1);
        subject.attach(observer2);

        subject.setSubjectState("Hello World ~");
    }
}
