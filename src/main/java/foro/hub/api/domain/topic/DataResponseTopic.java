package foro.hub.api.domain.topic;

import java.time.LocalDateTime;

public record DataResponseTopic(Long id, String title, String message, LocalDateTime creationDate, Boolean status,
        String author, String course) {
}
