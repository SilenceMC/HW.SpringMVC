package ru.netology.repository;

import org.springframework.stereotype.Repository;
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

    public synchronized Post save(Post post) {
        if (post.getId() == 0) {
            while (posts.containsKey(idCounter)) {
                ++idCounter;
            }
            post.setId(idCounter);
        }
        posts.put(post.getId(), post);
        return post;
    }

    public void removeById(long id) {
        posts.remove(id);
    }
}
