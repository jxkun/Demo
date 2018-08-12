package spring.main;

import org.apache.http.client.HttpClient;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringHttpclient {

    ApplicationContext context = null;

    @Before
    public void init(){
        context = new ClassPathXmlApplicationContext("classpath:/applicationContext-httpclient.xml");
    }

    @Test
    public void testHttpClient(){
        HttpClient bean1 = context.getBean(HttpClient.class);
        HttpClient bean2 = context.getBean(HttpClient.class);
        System.out.println(bean1 == bean2);
    }
}
