package org.yaremax.httpstreamingapipractice;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

@Service
public class DataStreamingService {

    public StreamingResponseBody streamTextData() {
        return new StreamingResponseBody() {
            @Override
            public void writeTo(OutputStream outputStream) throws IOException {
                try (PrintWriter writer = new PrintWriter(outputStream)) {
                    for (int i = 0; i < 10000; i++) {
                        writer.println("Text data line " + i);
                        writer.flush();
                        Thread.sleep(10); // simulate delay
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };
    }

}
