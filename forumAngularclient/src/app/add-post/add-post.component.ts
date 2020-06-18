import {Component, OnInit} from '@angular/core';
import {PostService} from '../_services/post.service';
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

  constructor(
    private postService: PostService,
    private route: ActivatedRoute,
    private router: Router,
    private tokenStorageService: TokenStorageService
  ) {
  }

  ngOnInit(): void {
    this.currentUser = this.tokenStorageService.getUser();

    this.route.paramMap.subscribe(() => {
      this.category = this.route.snapshot.params.category;
    });
  }

  onSubmit() {
    console.log(this.post);
    this.postService.addPost(this.post, this.currentUser.id, this.category).subscribe(() => this.goToPostsList());
  }

  goToPostsList() {
    this.router.navigate(['/posts/' + this.category]);
  }

}