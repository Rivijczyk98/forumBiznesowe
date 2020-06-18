export class Role {
    id: number;
    name: RoleEnum;
}

export enum RoleEnum {
    ROLE_USER,
    ROLE_VIP,
    ROLE_MODERATOR,
    ROLE_ADMIN
}
