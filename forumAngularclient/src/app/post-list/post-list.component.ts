import {Component, OnInit} from '@angular/core';
import {PostService} from '../_services/post.service';
import {Post} from '../_model/post';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.css']
})
export class PostListComponent implements OnInit {

  categoryId: number;
  posts: Post[] = [];

  constructor(private postService: PostService, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.categoryId = this.route.snapshot.params.id;
    });

    this.postService.getPostsByCategory(this.categoryId).subscribe(posts => {
      this.posts = posts;
    });
  }
}
