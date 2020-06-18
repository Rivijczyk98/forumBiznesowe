package pl.edu.pb.wi.forumbiznesowe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.Post;
import pl.edu.pb.wi.forumbiznesowe.service.PostServiceImpl;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
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
    public void addPost(@RequestBody Post post) {
        postService.add(post);
    }

    @PostMapping("/suggest")
    public void suggestPost(@RequestBody Post post) {
        postService.suggest(post);
    }

    @PutMapping
    public void updatePost(@RequestBody Post post) {
        postService.update(post);
    }

    @DeleteMapping
    public void deletePost(@RequestParam Long id) {
        postService.delete(id);
    }

    @GetMapping("/category")
    public Iterable<Post> getPostsByCategory(@RequestParam Long id){
        return postService.getPostsByCategory(id);
    }

    @PatchMapping("/observed")
    public void changeIsObserved(@RequestParam Boolean isObserved, @RequestBody Post post){ postService.changeIsObserved(isObserved, post);}

}
