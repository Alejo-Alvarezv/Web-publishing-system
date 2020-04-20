import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PostService } from 'src/app/services/post.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {
  private idPost;
  public post;
  public name;
  public comment;
 

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: PostService
  ) { }

  ngOnInit() {
    this.idPost = this.route.snapshot.paramMap.get('id');
    this.service.find(this.idPost)
        .subscribe(response =>{
          this.post = response;    
        },error => {
          this.router.navigate(['/']);
        });
   
  }

  public send(){
    const data ={
      'comment': this.comment,
      'name': this.name
    };

    this.service.addComment(this.idPost, data)
        .subscribe( response =>{
          this.ngOnInit();
        });
  }

}
