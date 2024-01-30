package org.yaremax.httpstreamingapipractice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@RestController
@RequestMapping("/api/v1/stream")
@RequiredArgsConstructor
public class DataStreamingController {

    private final DataStreamingService dataStreamingService;

    @GetMapping("/text-data")
    public ResponseEntity<StreamingResponseBody> streamTextData() {
        return ResponseEntity.ok()
                .header("Content-Type", "text/plain")
                .body(dataStreamingService.streamTextData());
    }

    @GetMapping("/json-data")
    public ResponseEntity<StreamingResponseBody> streamJsonData() {
        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(dataStreamingService.streamJsonData());
    }
}
