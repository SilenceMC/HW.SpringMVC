package ru.netology.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.netology.dto.PostDto;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.repository.PostRepository;
import ru.netology.util.PostDtoMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<PostDto> all() {
        return repository.all()
                .stream()
                .filter(post -> !post.isRemoved())
                .map(PostDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<PostDto> getById(long id) {
        return Optional.of(PostDtoMapper.toDto(repository.getById(id)
                .filter(post -> !post.isRemoved())
                .orElseThrow(NotFoundException::new)));
    }

    public Optional<PostDto> save(Post post) {
        return Optional.of(PostDtoMapper.toDto(repository.save(post).orElseThrow(NotFoundException::new)));
    }

    public void removeById(long id) {
        repository.removeById(id);
    }
}

