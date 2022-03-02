package hello.advanced.trace.strategy.code.template;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeLogTemplate {
    public void execute(Callback callback){
        long start = System.currentTimeMillis();
        //비즈니스 로직 실행
        callback.call();
        //비지니스 로직 종료
        long end = System.currentTimeMillis();
        long resultTime = end - start;
        log.info("resultTime={}",resultTime);
    }
}
