import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './home/home.component';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {AdministratorPanelComponent} from './administrator-panel/administrator-panel.component';
import {AdminguardService} from './_guards/adminguard.service';
import { PostComponent } from './post/post.component';
import {PostListComponent} from './post-list/post-list.component';
import {AddPostComponent} from './add-post/add-post.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'admin_panel', component: AdministratorPanelComponent, canActivate: [AdminguardService] },
  { path: 'posts/:category', component: PostListComponent, pathMatch: 'full' },
  { path: 'post/:id', component: PostComponent, pathMatch: 'full' },
  { path: 'addpost/:category', component: AddPostComponent, pathMatch: 'full' },
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
