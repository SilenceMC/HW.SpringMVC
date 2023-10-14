package ru.netology.util;

import ru.netology.dto.PostDto;
import ru.netology.model.Post;


public class PostDtoMapper {
    //из entity в dto
    public static PostDto toDto(Post post) {
        return new PostDto(post.getId(), post.getContent());
    }
}
