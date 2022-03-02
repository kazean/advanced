package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.strategy.ContextV1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {
    @Test
    void strategyV0(){
        logic1();
        logic2();
    }

    private void logic1() {
        long start = System.currentTimeMillis();
        //비즈니스 로직 실행
        log.info("비지니스 로직실행1");
        //비지니스 로직 종료
        long end = System.currentTimeMillis();
        long resultTime = end - start;
        log.info("resultTime={}",resultTime);
    }

    private void logic2() {
        long start = System.currentTimeMillis();
        //비즈니스 로직 실행
        log.info("비지니스 로직실행2");
        //비지니스 로직 종료
        long end = System.currentTimeMillis();
        long resultTime = end - start;
        log.info("resultTime={}",resultTime);
    }

    /**
     * 전략 패턴 적용
     */
    @Test
    void strategyV1(){
        ContextV1 context1 = new ContextV1(new StrategyLogic1());
        context1.execute();
        ContextV1 context2 = new ContextV1(new StrategyLogic2());
        context2.execute();
    }

    /**
     * 전략 패턴 익명내부클래스2,3 or 람다4
     */
    @Test
    void strategyV2_3_4(){
        ContextV1 context1 = new ContextV1(() -> log.info("비지니스 로직1 실행 lambda"));
        context1.execute();
        ContextV1 context2 = new ContextV1(() -> log.info("비지니스 로직2 실행 lambda"));
        context2.execute();
    }

}
