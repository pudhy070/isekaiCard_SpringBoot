package com.card.isekai.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
public class EventDto {

    @Getter
    @Setter
    public static class Response {
        private Long id;
        private String title;
        private String content;
        private String author;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Getter
    @Setter
    public static class CreateRequest {
        private String title;
        private String content;
        private String author;
    }

    @Getter
    @Setter
    public static class UpdateRequest {
        private String title;
        private String content;
    }
}

