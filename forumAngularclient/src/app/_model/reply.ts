import { User } from './user';
import { Post } from './post';

export class Reply {
    id: number;
    author: User;
    text: string;
    post: Post;
    postedDate: Date;
}