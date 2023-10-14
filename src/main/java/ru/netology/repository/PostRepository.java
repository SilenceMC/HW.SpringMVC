package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.*;


// Stub
@Repository
public class PostRepository {

    private long idCounter;
    private final Map<Long, Post> posts = new HashMap<>();

    public List<Post> all() {
        return new ArrayList<>(posts.values());
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(posts.get(id));
    }

    public synchronized Optional<Post> save(Post post) {
        while (posts.containsKey(idCounter)) {
            ++idCounter;
        }
        if (post.getId() == 0) {
            post.setId(idCounter);
            posts.put(post.getId(), post);
            return Optional.of(post);
        } else if (!posts.containsKey(post.getId()) || !posts.get(post.getId()).isRemoved()) {
            posts.put(post.getId(), post);
            return Optional.of(post);
        }
        return Optional.empty();
    }

    public void removeById(long id) {
        if (posts.containsKey(id)) posts.get(id).setRemoved(true);
    }
}
