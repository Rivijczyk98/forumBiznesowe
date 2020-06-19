import {Component, Input, OnInit} from '@angular/core';
import {Reply} from '../_model/reply';
import {ReplyService} from '../_services/reply.service';
import {ReportService} from '../_services/report.service';
import {TokenStorageService} from '../_services/token-storage.service';
import {Post} from '../_model/post';
import {UserService} from '../_services/user.service';

@Component({
  selector: 'app-reply',
  templateUrl: './reply.component.html',
  styleUrls: ['./reply.component.css']
})
export class ReplyComponent implements OnInit {

  @Input() object: any = {};

  reply: Reply = null;
  post: Post = new Post();
  username: string = '';

  reporting: boolean = false;

  constructor(
    private reportService: ReportService,
    private tokenStorage: TokenStorageService,
    private replyService: ReplyService,
    private userService: UserService) {
  }

  ngOnInit(): void {
    this.reply = this.object.reply;
    this.post = this.object.post;

    this.userService.getUsername(this.reply.author).subscribe(u => {
      this.username = u.username;
    });
  }

  // report(text: string){
  //
  //   this.reportService.addReport(
  //     new Report(
  //       this.tokenStorage.getUser(),
  //       ReportedObjectEnum.REPLY,
  //       this.post.id,
  //       text
  //     )
  //   )
  // }

  hasRoleHigher(n: number): boolean {
    return this.tokenStorage.getUser().roles[0].name > n;
  }

  isAuthor(): boolean {
    return this.tokenStorage.getUser().id == this.reply.author;
  }

  delete() {
    this.replyService.deleteReply(this.reply.id);
    this.ngOnInit();
  }

}
