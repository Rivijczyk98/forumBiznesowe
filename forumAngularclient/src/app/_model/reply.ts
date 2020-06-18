import {User} from './user';
import {Post} from './post';

export class Reply {
  id: number;
  author: User;
  text: string;
  post: Post;
  postedDate: Date;

  constructor(author, text, post, postedDate) {
    this.author = author;
    this.text = text;
    this.post = post;
    this.postedDate = postedDate;
  }
}
