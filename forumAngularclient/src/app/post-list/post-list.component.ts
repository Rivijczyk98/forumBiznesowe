import { Component, OnInit } from '@angular/core';
import {PostsService} from '../_services/posts.service';
import {Post} from '../_model/post';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.css']
})
export class PostListComponent implements OnInit {

  category: string;
  posts: Post[] = [];

  constructor(private postService: PostsService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.category = this.route.snapshot.params.category;
    });

    this.postService.getPostsByCategory(this.category).subscribe(posts => {
      this.posts = posts;
    });
  }
}
