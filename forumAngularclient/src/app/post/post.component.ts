import {Component, OnInit} from '@angular/core';
import {PostService} from '../_services/post.service';
import {Post} from '../_model/post';
import {ActivatedRoute, Router} from '@angular/router';
import {ReplyService} from '../_services/reply.service';
import {Reply} from '../_model/reply';
import {TokenStorageService} from '../_services/token-storage.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  editing = false;
  reporting = false;
  form: any = {};
  editForm: any = {};
  post: Post = new Post();
  replies: Reply[] = [];
  id: number = null;

  constructor(
    private postService: PostService,
    private replyService: ReplyService,
    private route: ActivatedRoute,
    private router: Router,
    private tokenService: TokenStorageService) {
    this.load();
  }

  ngOnInit(): void {

  }

  async load() {
    this.route.paramMap.subscribe(params => {
      this.id = +params.get('id');
    });

    this.postService.findById(this.id).subscribe(p => {
      this.post = p;
      this.post.postedDate = new Date(p.postedDate);
    });

    this.replyService.findAllByPost(this.id).subscribe(r => {
      r.forEach(rep => {
        this.replies.push({id: rep.id, author: rep.author.id, text: rep.text, post: rep.post.id, postedDate: new Date(rep.postedDate)});
      });
    });
  }

  reply() {
    const newReply: Reply = new Reply(this.tokenService.getUser().id, this.form.text, this.post.id, new Date());
    this.replyService.addReply(newReply).subscribe(res => {
      this.ngOnInit();
    });
    this.replies.push(newReply);
  }

  changeIsObserved(isO: boolean) {
    this.postService.changeIsObserved(isO, this.post);
    this.post.isObserved = isO;
  }

  delete() {
    const categoryID: number = this.post.category.id;
    this.postService.deletePost(this.post.id);

    this.router.navigateByUrl('/category?id=' + categoryID);
  }

  isLoggedIn() {
    return !!this.tokenService.getToken();
  }

  editPost() {
    this.reporting = false;
    this.editing = true;
  }

  edit() {
    this.post.text = this.editForm.text;
    this.postService.updatePost(this.post);
    this.editing = false;
    this.ngOnInit();
  }

}
