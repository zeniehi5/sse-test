package org.test.sse.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class Sse {

    private final Long userId;
    private final SseEmitter emitter;

    public Sse(Long userId) {
        this.userId = userId;
        this.emitter = new SseEmitter();
    }

    public Sse connect() {
        try {
            emitter.send(SseEmitter.event()
                    .name("connect")
                    .data("test data"));
        } catch (IOException e) {
            throw new IllegalStateException();
        }
        return this;
    }

    public Boolean equalsUserId(Long userId) {
        return Objects.equals(this.userId, userId);
    }


    public void send(Object message) {
        try {
            emitter.send(message);
        } catch (IOException e) {
            log.error("전송 실패");
            throw new RuntimeException(userId + "에게 전송 실패");
        }
    }
}
