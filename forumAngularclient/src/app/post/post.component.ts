import { Component, OnInit } from '@angular/core';
import { PostsService } from '../_services/posts.service';
import { Observable } from 'rxjs';
import { Post } from '../_model/post';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  post: Post
  id: number;

  constructor(postService: PostsService, route: ActivatedRoute) {
    
    route.paramMap.subscribe(params => {
      this.id = params["id"];  
    })

    postService.findById(this.id).subscribe(p => {
      this.post = p;
    });

   }

  ngOnInit(): void {

  }

}
