import { User } from './user';

export class Report {
    id: number;
    author: User;
    reportedObjectName: ReportedObjectEnum;
    reportedObjectId: number;
    text: string;

    public constructor(author: User, 
        reportedObjectName: ReportedObjectEnum, 
        reportedObjectId: number, 
        text: string){
            this.author = author;
            this.reportedObjectId = reportedObjectId;
            this.reportedObjectName = reportedObjectName;
            this.text = text;
    }
}

export enum ReportedObjectEnum {
    POST,
    REPLY
}