import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {authInterceptorProviders} from './_helpers/auth.interceptor';
import {HomeComponent} from './home/home.component';
import {RegisterComponent} from './register/register.component';
import {LoginComponent} from './login/login.component';
import {PostComponent} from './post/post.component';
import {PostedDatePipe} from './_pipes/posted-date.pipe';
import {PostSummaryPipe} from './_pipes/post-summary.pipe';
import {AdministratorPanelComponent} from './administrator-panel/administrator-panel.component';
import {PostListComponent} from './post-list/post-list.component';
import {AddPostComponent} from './add-post/add-post.component';
import {CategoryAddComponent} from './category-add/category-add.component';
import {ReplyComponent} from './reply/reply.component';
import {PostsPendedListComponent} from './posts-pended-list/posts-pended-list.component';
import {CategoryEditComponent} from './category-edit/category-edit.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    RegisterComponent,
    LoginComponent,
    PostComponent,
    PostedDatePipe,
    PostSummaryPipe,
    LoginComponent,
    AdministratorPanelComponent,
    PostListComponent,
    AddPostComponent,
    CategoryAddComponent,
    ReplyComponent,
    AddPostComponent,
    PostsPendedListComponent,
    CategoryEditComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
