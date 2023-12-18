package org.test.sse.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.test.sse.domain.Sse;
import org.test.sse.service.SseService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SseController {

    private SseService sseService;

    @GetMapping(value = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<?> connect(@RequestParam("userId") Long userId) {
        Sse connected = sseService.connect(userId);

        return ResponseEntity.ok(null);
    }

    @PostMapping("/send/{userId}")
    public ResponseEntity<?> send(@PathVariable Long userId, @RequestBody Object obj) {
        sseService.send(userId, obj);
        return ResponseEntity.ok(null);
    }

}
