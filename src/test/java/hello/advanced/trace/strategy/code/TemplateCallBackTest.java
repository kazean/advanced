package hello.advanced.trace.strategy.code;

import hello.advanced.trace.strategy.code.template.TimeLogTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateCallBackTest {

    /**
     * 템플릿 콜백 패턴 + 익명클래스, 람다
     */
    @Test
    void callbackV1_2_3(){
        TimeLogTemplate template = new TimeLogTemplate();
        template.execute(() -> log.info("비지니스 로직1 callback"));
        template.execute(() -> log.info("비지니스 로직2 callback"));
    }
}
