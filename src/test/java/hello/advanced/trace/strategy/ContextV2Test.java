package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.strategy.ContextV2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {

    /**
     * 전략패턴 파라미터화 + 람다
     */
    @Test
    void strategyV1_2_3(){
        ContextV2 context = new ContextV2();
        context.execute(() -> log.info("비지니스 로직1 실행 param"));
        context.execute(()->log.info("비지니스 로직2 실행 param"));
    }
}
