import { User } from './user';

export class Report {
    id: number;
    author: User;
    reportedObjectName: ReportedObjectEnum;
    reportedObjectId: number;
    text: string;
}

export enum ReportedObjectEnum {
    POST,
    REPLY
}