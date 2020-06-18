import { Component, OnInit } from '@angular/core';
import { PostsService } from '../_services/posts.service';
import { Observable } from 'rxjs';
import { Post } from '../_model/post';
import { Router, ActivatedRoute } from '@angular/router';
import { RepliesService } from '../_services/replies.service';
import { Reply } from '../_model/reply';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  form: any = {};
  post: Post
  replies: Reply[] = [];
  id: number;

  constructor(public postService: PostsService, public repliesService: RepliesService, route: ActivatedRoute, public router: Router, public tokenService: TokenStorageService) {
    
    route.paramMap.subscribe(params => {
      this.id = params["id"];  
    })

    postService.findById(this.id).subscribe(p => {
      this.post = p;
    });

    repliesService.findAllByPost(this.id).subscribe(r => {
      r.sort((a, b) => a.postedDate.getDate() - b.postedDate.getDate());
      this.replies = r;
    })

  }

  ngOnInit(): void {
  }

  reply(){
    var newReply: Reply = new Reply(this.tokenService.getUser(), this.form.text, this.post, new Date());
    this.repliesService.addReply(newReply)
    this.replies.push(newReply);
  }

  changeIsObserved(isO: boolean){
    this.postService.changeIsObserved(isO, this.post)
    this.post.isObserved = !this.post.isObserved;
  }

  delete(){
    var categoryID: number = this.post.category.id;
    this.postService.deletePost(this.post.id);
    
    this.router.navigateByUrl('/category?id=' + categoryID)
  }

}
