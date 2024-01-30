package org.yaremax.httpstreamingapipractice;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class DataStreamingService {

    private final ObjectMapper objectMapper;

    public StreamingResponseBody streamTextData() {
        return outputStream -> {
            try (PrintWriter writer = new PrintWriter(outputStream)) {
                for (int i = 0; i < 1000; i++) {
                    writer.println("Text data line " + i);
                    writer.flush();
                    Thread.sleep(10); // simulate delay
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };
    }

    public StreamingResponseBody streamJsonData() {
        return new StreamingResponseBody() {
            @Override
            public void writeTo(OutputStream outputStream) throws IOException {
                try (PrintWriter writer = new PrintWriter(outputStream)) {

                    MessageEntity message1 = new MessageEntity(1L, "Hello World!", "Alice", LocalDateTime.now());
                    MessageEntity message2 = new MessageEntity(2L, "Good morning", "Bob", LocalDateTime.now());
                    MessageEntity message3 = new MessageEntity(3L, "How are you?", "Charlie", LocalDateTime.now());
                    MessageEntity message4 = new MessageEntity(4L, "I'm fine, thanks!", "Alice", LocalDateTime.now());
                    MessageEntity message5 = new MessageEntity(5L, "See you later.", "Dave", LocalDateTime.now());

                    String json1 = objectMapper.writeValueAsString(message1);
                    String json2 = objectMapper.writeValueAsString(message2);
                    String json3 = objectMapper.writeValueAsString(message3);
                    String json4 = objectMapper.writeValueAsString(message4);
                    String json5 = objectMapper.writeValueAsString(message5);

                    for (int i = 0; i < 10; i++) {
                        outputStream.write(json1.getBytes(StandardCharsets.UTF_8));
                        writer.flush();
                        Thread.sleep(1000);
                        outputStream.write(json2.getBytes(StandardCharsets.UTF_8));
                        writer.flush();
                        Thread.sleep(1000);
                        outputStream.write(json3.getBytes(StandardCharsets.UTF_8));
                        writer.flush();
                        Thread.sleep(1000);
                        outputStream.write(json4.getBytes(StandardCharsets.UTF_8));
                        writer.flush();
                        Thread.sleep(1000);
                        outputStream.write(json5.getBytes(StandardCharsets.UTF_8));
                        writer.flush();
                        Thread.sleep(1000);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }


}
