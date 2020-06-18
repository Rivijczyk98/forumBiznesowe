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

  editing: boolean = false;
  reporting: boolean = false;
  form: any = {};
  editForm: any = {};
  post: Post = new Post();
  replies: Reply[] = [];
  id: number = null;

  constructor(
    private postService: PostsService,
    private repliesService: RepliesService,
    private route: ActivatedRoute,
    private router: Router,
    private tokenService: TokenStorageService) {
      this.load();
  }

  ngOnInit(): void {

  }

  async load(){
    this.route.paramMap.subscribe(params => {
      this.id = +params.get('id');
    })

    this.postService.findById(this.id).subscribe(p => {
      this.post = p;
      this.post.postedDate = new Date(p.postedDate)
    });

    this.repliesService.findAllByPost(this.id).subscribe(r => {
      r.forEach(rep => {
        this.replies.push({id: rep.id, author: rep.author.id, text: rep.text, post: rep.post.id, postedDate: new Date(rep.postedDate)});
      });
    })
  }

  reply(){
    var newReply: Reply = new Reply(this.tokenService.getUser().id, this.form.text, this.post.id, new Date());
    this.repliesService.addReply(newReply).subscribe(res => {
      this.ngOnInit();
    });
    this.replies.push(newReply);
  }

  changeIsObserved(isO: boolean){
    this.postService.changeIsObserved(isO, this.post)
    this.post.isObserved = isO;
  }

  delete(){
    var categoryID: number = this.post.category.id;
    this.postService.deletePost(this.post.id);

    this.router.navigateByUrl('/category?id=' + categoryID)
  }

  isLoggedIn(){
    return !!this.tokenService.getToken();
  }

  editPost(){
    this.reporting = false;
    this.editing = true;
  }

  edit(){
    this.post.text = this.editForm.text
    this.postService.updatePost(this.post);
    this.editing = false;
    this.ngOnInit();
  }

}
