import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PostsComponent } from './components/posts/posts.component';
import { NewPostComponent } from './components/new-post/new-post.component';
import { CommentComponent } from './components/comment/comment.component';

const routes: Routes = [
  {
    path:'',
    redirectTo:'posts',
    pathMatch:'full'
  },
  {
    path:'posts',
    component: PostsComponent
  },
  {
    path: 'new-post',
    component: NewPostComponent
  },
  {
    path:'post/:id',
    component: CommentComponent
  },
  {
    path:'post/search/:id',
    component: PostsComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
