package foro.hub.api.domain.topic;

import jakarta.validation.constraints.NotBlank;

public record DataCreateTopic(
        @NotBlank String title,
        @NotBlank String message,
        @NotBlank String author,
        @NotBlank String course) {
}
