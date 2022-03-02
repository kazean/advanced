package hello.advanced.trace.template.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate {
    public void execute(){
        long start = System.currentTimeMillis();
        //비즈니스 로직 실행
        call();
        //비지니스 로직 종료
        long end = System.currentTimeMillis();
        long resultTime = end - start;
        log.info("resultTime={}",resultTime);
    }

    protected abstract void call();
}
