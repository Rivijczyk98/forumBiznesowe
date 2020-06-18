import { User } from './user';
import { Category } from './category';
import { Status } from './status';

export class Post{
    id: number;
    author: User;
    category: Category;
    title: string;
    text: string;
    status: Status;
    postedDate: Date;
    isObserved: boolean;
}