import {Component, OnInit} from '@angular/core';
import {PostService} from '../_services/post.service';
import {Post} from '../_model/post';
import {ActivatedRoute} from '@angular/router';
import {ReplyService} from '../_services/reply.service';
import {Reply} from '../_model/reply';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  post: Post;
  replies: Reply[] = [];
  id: number;

  constructor(postService: PostService, replies: ReplyService, route: ActivatedRoute) {

    route.paramMap.subscribe(params => {
      this.id = params['id'];
    });

    postService.findById(this.id).subscribe(p => {
      this.post = p;
    });

    replies.findAllByPost(this.id).subscribe(r => {
      r.sort((a, b) => a.postedDate.getDate() - b.postedDate.getDate());
      this.replies = r;
    })

  }

  ngOnInit(): void {

  }

}
