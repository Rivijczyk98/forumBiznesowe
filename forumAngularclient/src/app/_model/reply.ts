import { User } from './user';
import { Post } from './post';

export class Reply {
    id: number;
    author: number;
    text: string;
    post: number;
    postedDate: Date;

    constructor(author, text, post, postedDate){
        this.author = author;
        this.text = text;
        this.post = post;
        this.postedDate = postedDate;
    }
}