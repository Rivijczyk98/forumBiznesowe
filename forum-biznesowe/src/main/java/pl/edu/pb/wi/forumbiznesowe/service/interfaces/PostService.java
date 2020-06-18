package pl.edu.pb.wi.forumbiznesowe.service.interfaces;

import pl.edu.pb.wi.forumbiznesowe.dao.entity.Post;
import pl.edu.pb.wi.forumbiznesowe.pojo.PostRequest;

import java.util.Optional;

public interface PostService {

    Iterable<Post> findAll();

    Optional<Post> find(Long id);

    void add(Post post);

    void suggest(Post post);

    void update(PostRequest post);

    void delete(Post post);

    void delete(Long id);

}
