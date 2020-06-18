package pl.edu.pb.wi.forumbiznesowe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.Post;
import pl.edu.pb.wi.forumbiznesowe.pojo.PostRequest;
import pl.edu.pb.wi.forumbiznesowe.service.PostServiceImpl;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostServiceImpl postService;

    @Autowired
    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }

    @GetMapping("/all")
    public Iterable<Post> getAll(){
        return postService.findAll();
    }

    @GetMapping
    public Optional<Post> getPost(@RequestParam Long id) {
        return postService.find(id);
    }

    @PostMapping
    public void addPost(@RequestBody Post post, @RequestParam long idUser, @RequestParam String categoryName) {
        postService.add(post, idUser, categoryName);
    }

    @PostMapping("/suggest")
    public void suggestPost(@RequestBody Post post, @RequestParam long idUser, @RequestParam String categoryName) {
        postService.suggest(post, idUser, categoryName);
    }

    @PutMapping
    public void updatePost(@RequestBody PostRequest post) {
        postService.update(post);
    }

    @DeleteMapping
    public void deletePost(@RequestParam Long id) {
        postService.delete(id);
    }

    @GetMapping("/category")
    public Iterable<Post> getPostsByCategory(@RequestParam String name){
        return postService.getPostsByCategory(name);
    }

    @PatchMapping("/observed")
    public void changeIsObserved(@RequestParam Boolean isObserved, @RequestBody Post post){ postService.changeIsObserved(isObserved, post);}

}
