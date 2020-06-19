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
    public Iterable<Post> getAll() {
        return postService.findAll();
    }

    @GetMapping
    public Optional<Post> getPost(@RequestParam Long id) {
        return postService.find(id);
    }

    @GetMapping("/category")
    public Iterable<Post> getPostsByCategory(@RequestParam Long id) {
        return postService.getPostsByCategory(id);
    }

    @PostMapping("/add")
    public void addPost(@RequestBody Post post, @RequestParam Long idUser, @RequestParam Long categoryId) {
        postService.add(post, idUser, categoryId);
    }

    @PostMapping("/accept")
    public void acceptPost(@RequestBody Post post) {
        postService.accept(post);
    }

    @PutMapping("/update")
    public void updatePost(@RequestBody Post post) {
        postService.update(post);
    }

    @PutMapping("/approve")
    public void setAsApproved(@RequestBody Post post) {
        postService.setAsApproved(post);
    }

    @DeleteMapping("/delete")
    public void deletePost(@RequestParam Long id) {
        postService.delete(id);
    }

    @PatchMapping("/observed")
    public void changeIsObserved(@RequestParam Boolean isObserved, @RequestBody Post post) {
        postService.changeIsObserved(isObserved, post);
    }

    @GetMapping("/category-pended")
    public Iterable<Post> getPendedPosts(){
        return postService.getPendedPosts();
    }

    public void changeIsObserved(@RequestParam Long id){ postService.changeIsObserved(id);}

}
