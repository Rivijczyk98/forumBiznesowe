package pl.edu.pb.wi.forumbiznesowe.services.interfaces;

import pl.edu.pb.wi.forumbiznesowe.dao.entity.Post;

import java.util.Optional;

public interface PostServiceInterface {

    Iterable<Post> findAll();
    Optional<Post> find(Long id);

    Post add(Post post);

    void update(Post post);

    void delete(Post post);
    void delete(Long id);
}
