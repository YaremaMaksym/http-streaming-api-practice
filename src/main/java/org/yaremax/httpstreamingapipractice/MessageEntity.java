package org.yaremax.httpstreamingapipractice;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MessageEntity {
    private Long id;
    private String content;
    private String author;
    private LocalDateTime createdAt;
}
