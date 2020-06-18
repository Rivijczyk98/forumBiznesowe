import { Component, OnInit } from '@angular/core';
import {PostsService} from '../_services/posts.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Post} from '../_model/post';
import {TokenStorageService} from '../_services/token-storage.service';

@Component({
  selector: 'app-add-post',
  templateUrl: './add-post.component.html',
  styleUrls: ['./add-post.component.css']
})
export class AddPostComponent implements OnInit {

  post: Post = new Post();
  category: string;

  currentUser: any;

  constructor(private postService: PostsService, private route: ActivatedRoute,  private router: Router,  private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.currentUser = this.tokenStorageService.getUser();

    this.route.paramMap.subscribe(params => {
      this.category = this.route.snapshot.params.category;
    });
  }

  onSubmit() {
    this.postService.addPost(this.post, this.currentUser.id, this.category).subscribe(result => this.goToPostsList());
  }

  goToPostsList() {
    this.router.navigate(['/posts/' + this.category]);
  }

}
