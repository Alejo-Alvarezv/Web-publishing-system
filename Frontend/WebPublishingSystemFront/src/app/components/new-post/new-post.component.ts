import { Component, OnInit } from '@angular/core';
import { PostService } from 'src/app/services/post.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-post',
  templateUrl: './new-post.component.html',
  styleUrls: ['./new-post.component.css']
})
export class NewPostComponent implements OnInit {
  public name;
  public post;
  public description;

  constructor(
    private postService: PostService,
    private router: Router
  ) { }

  ngOnInit() {
  }

  public send(){
    const post = {
      'name': this.name,
      'text': this.post,
      'description': this.description
    };

    this.postService.save(post)
        .subscribe(response =>{
          this.router.navigate(['/' ]);
        })
  }

}
