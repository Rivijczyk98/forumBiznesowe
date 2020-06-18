import { Component, OnInit } from '@angular/core';
import {User} from '../_model/user';
import {UserService} from '../_services/user.service';

@Component({
  selector: 'app-administrator-panel',
  templateUrl: './administrator-panel.component.html',
  styleUrls: ['./administrator-panel.component.css']
})
export class AdministratorPanelComponent implements OnInit {

  users: User[] = [];

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.refreshData();
  }

  makePrettyRoleName(name: string) {
    return name.substr(5).toLowerCase();
  }

  delete(id: number) {
    this.userService.deleteUser(id).subscribe(() => this.refreshData());
  }

  private refreshData() {
    this.userService.findAll().subscribe(data => {
      this.users = data;
    });
  }

  giveRole(user: User, role: string) {
    this.userService.giveRole(user, role).subscribe(() => this.refreshData());
  }
}
