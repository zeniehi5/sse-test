package org.test.sse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.test.sse.domain.Sse;
import org.test.sse.repository.SseRepository;

@Service
@RequiredArgsConstructor
public class SseService {
    private SseRepository sseRepository;
    public Sse connect(Long userId) {

        Sse sse = new Sse(userId);

        Sse connectedSse = sse.connect();

        sseRepository.save(connectedSse);

        return connectedSse;
    }

    public void send(Long userId, Object obj) {
        Sse byUserId = sseRepository.findByUserId(userId);
        byUserId.send(obj);
    }
}
