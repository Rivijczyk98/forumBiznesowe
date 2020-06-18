export class Status{
    id: number;
    name: PostStatusEnum;
}

export enum PostStatusEnum {
    PENDING,
    APPROVED,
    DENIED,
    REPORTED,
    DELETED
}