import {Component, OnInit} from '@angular/core';
import {PostService} from '../_services/post.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Post} from '../_model/post';
import {TokenStorageService} from '../_services/token-storage.service';
import {CategoryService} from '../_services/category.service';
import {Category} from '../_model/category';

@Component({
  selector: 'app-add-post',
  templateUrl: './add-post.component.html',
  styleUrls: ['./add-post.component.css']
})
export class AddPostComponent implements OnInit {

  post: Post = new Post();
  categoryId: number;

  currentUser: any;

  constructor(
    private postService: PostService,
    private route: ActivatedRoute,
    private router: Router,
    private tokenStorageService: TokenStorageService,
  ) {
  }

  ngOnInit(): void {
    this.currentUser = this.tokenStorageService.getUser();

    this.route.paramMap.subscribe(params => {
      this.categoryId = +params.get('id');
    });
  }

  onSubmit() {
    this.postService.addPost(this.post, this.currentUser.id, this.categoryId).subscribe(() => this.goToPostsList());
  }

  goToPostsList() {
    this.router.navigate(['/categories/' + this.categoryId]);
  }

}
