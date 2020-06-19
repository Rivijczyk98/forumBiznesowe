import { Component, OnInit } from '@angular/core';
import {Post} from '../_model/post';
import {PostsService} from '../_services/posts.service';
import {PostStatusEnum} from '../_model/status';

@Component({
  selector: 'app-posts-pended-list',
  templateUrl: './posts-pended-list.component.html',
  styleUrls: ['./posts-pended-list.component.css']
})
export class PostsPendedListComponent implements OnInit {

  posts: Post[] = [];

  constructor(private postService: PostsService) { }

  ngOnInit(): void {
    this.postService.getPendedPosts().subscribe(posts => {
      this.posts = posts;
    });
  }

  accept(post: Post) {
    this.postService.approvePost(post).subscribe(() => this.ngOnInit());
  }

  deny(post: Post) {
    this.postService.deletePost(post.id).subscribe(() => this.ngOnInit());
  }
}
