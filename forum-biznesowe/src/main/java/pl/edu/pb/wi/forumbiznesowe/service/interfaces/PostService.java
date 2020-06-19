package pl.edu.pb.wi.forumbiznesowe.service.interfaces;

import pl.edu.pb.wi.forumbiznesowe.dao.entity.Post;
import pl.edu.pb.wi.forumbiznesowe.pojo.PostRequest;

import java.util.Optional;

public interface PostService {

    Iterable<Post> findAll();

    Optional<Post> find(Long id);

    void update(PostRequest post);

    void delete(Post post);

    void delete(Long id);

    void add(Post post, long idUser, Long categoryId);

    void accept(Post post);

    void changeIsObserved(Boolean isObserved, Post post);

}
