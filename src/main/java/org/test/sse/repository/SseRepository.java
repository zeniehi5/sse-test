package org.test.sse.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.test.sse.domain.Sse;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@Repository
public class SseRepository {
    private static final List<Sse> SSES = new CopyOnWriteArrayList<>();

    public void save(Sse sse) {
        SSES.add(sse);

        log.info("New emitter is added to SseEmitters : {}", sse);
        log.info("Size of SseEmitters : {}", SSES.size());

        SseEmitter emitter = sse.getEmitter();

        emitter.onTimeout(() -> {
            System.out.println("timeOut");
            SSES.remove(sse);
        });
        emitter.onCompletion(() -> {
            System.out.println("Completion");
            SSES.remove(sse);
        });

    }

    public Sse findByUserId(Long userId) {
        return SSES.stream()
                .filter(sse -> sse.equalsUserId(userId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("해당 유저는 커넥션 목록에 없습니다."));
    }
}
