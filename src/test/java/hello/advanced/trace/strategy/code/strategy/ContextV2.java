package hello.advanced.trace.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContextV2 {

    public void execute(Strategy strategy){
        long start = System.currentTimeMillis();
        //비즈니스 로직 실행
        strategy.call();
        //비지니스 로직 종료
        long end = System.currentTimeMillis();
        long resultTime = end - start;
        log.info("resultTime={}",resultTime);
    }
}
