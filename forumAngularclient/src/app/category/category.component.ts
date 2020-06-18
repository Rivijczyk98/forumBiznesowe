import { Component, OnInit, Input } from '@angular/core';
import { Category } from '../_model/category';
import { PostsService } from '../_services/posts.service';
import { Post } from '../_model/post';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  @Input() category: Category;

  posts: Post[] = [];

  constructor(postService: PostsService) {
    postService.getPostsByCategory(this.category.id).subscribe(p => {
      this.posts = p;
    })
  }

  ngOnInit(): void {
  }

}
