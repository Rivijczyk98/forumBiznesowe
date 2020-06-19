import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './home/home.component';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {AdministratorPanelComponent} from './administrator-panel/administrator-panel.component';
import {AdminguardService} from './_guards/adminguard.service';
import {PostComponent} from './post/post.component';
import {CategoryAddComponent} from './category-add/category-add.component';
import {PostListComponent} from './post-list/post-list.component';
import {AddPostComponent} from './add-post/add-post.component';
import {PostsPendedListComponent} from './posts-pended-list/posts-pended-list.component';
import {ModguardService} from './_guards/modguard.service';
import {VipguardService} from './_guards/vipguard.service';
import {UserguardService} from './_guards/userguard.service';

const routes: Routes = [
  {path: 'categories', component: HomeComponent},
  {path: 'categories/new', component: CategoryAddComponent, canActivate: [UserguardService]},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'admin_panel', component: AdministratorPanelComponent, canActivate: [AdminguardService]},
  {path: 'categories/:id', component: PostListComponent, pathMatch: 'full'},
  {path: 'post/:id', component: PostComponent, pathMatch: 'full'},
  {path: 'addpost/:id', component: AddPostComponent, canActivate: [UserguardService]},
  {path: 'posts_pended', component: PostsPendedListComponent, canActivate: [ModguardService]},
  {path: '', redirectTo: 'categories', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
