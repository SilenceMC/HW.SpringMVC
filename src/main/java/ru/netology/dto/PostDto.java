package ru.netology.dto;

import org.springframework.stereotype.Component;

public class PostDto {

    private long id;
    private String content;

    public PostDto(long id, String content) {
        this.id = id;
        this.content = content;
    }
}
