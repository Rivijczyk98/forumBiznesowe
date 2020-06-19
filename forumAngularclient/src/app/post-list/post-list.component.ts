import {Component, OnInit} from '@angular/core';
import {PostService} from '../_services/post.service';
import {Post} from '../_model/post';
import {ActivatedRoute} from '@angular/router';
import {CategoryService} from '../_services/category.service';
import {Category} from '../_model/category';

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.css']
})
export class PostListComponent implements OnInit {

  categoryId: number;
  category: Category = null;
  posts: Post[] = [];

  constructor(
    private postService: PostService,
    private categoryService: CategoryService,
    private route: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.categoryId = +params.get('id');
    });

    this.postService.getPostsByCategory(this.categoryId).subscribe(posts => {
      this.posts = posts;
    });

    this.categoryService.findById(this.categoryId).subscribe(category => {
      this.category = category;
    });
  }
}
