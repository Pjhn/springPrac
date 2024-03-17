package hello.core.singleton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService sta1 = ac.getBean(StatefulService.class);
        StatefulService sta2 = ac.getBean(StatefulService.class);

        //ThreadA : A사용자 10000원 주문
        sta1.order("userA", 10000);
        //ThreadB : B사용자 20000원 주문
        sta2.order("userA", 20000);

        //ThreadA: 사용자 주문금액 조회
        int price = sta1.getPrice();
        System.out.println("price = " + price);

        assertThat(sta1.getPrice()).isEqualTo(20000);

    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return  new StatefulService();
        }
    }

}