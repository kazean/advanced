package hello.advanced.trace.logtrace;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldLogTrace implements LogTrace {

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

//    traceId 동기화, 동시성 이슈 발생
    private TraceId traceHolder;
    
    @Override
    public TraceStatus begin(String message) {
//        TraceId traceId = new TraceId();
        syncTraceId();
        TraceId traceId = traceHolder;
        long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}",traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
        return new TraceStatus(traceId, startTimeMs, message);
    }

    @Override
    public void end(TraceStatus status) {
        complete(status, null);
    }

    @Override
    public void exception(TraceStatus status, Exception e) {
        complete(status,e);
    }

    private void syncTraceId() {
        if(traceHolder == null){
            traceHolder = new TraceId();
        }else{
            traceHolder = traceHolder.createNextId();
        }
    }

    private void releaseTraceId(){
        if(traceHolder.isFirstLevel()){
            traceHolder = null;
        }else{
            traceHolder.createPreviousId();
        }
    }

    private void complete(TraceStatus status, Exception e){
        long stopTimeMs = System.currentTimeMillis();
        long resultTimeMs = stopTimeMs - status.getStartTimeMs();
        TraceId traceId = status.getTraceId();

        if(e == null){
            log.info("[{}] {}{} time={}ms", traceId.getId(), addSpace(COMPLETE_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs);
        }else{
            log.info("[{}] {}{} time={}ms ex={}", traceId.getId(), addSpace(EX_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs, e.toString());
        }
    }

    private static String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < level; i++){
            sb.append((i == level - 1 ? "|" + prefix : "| "));
        }
        return sb.toString();
    }
}
