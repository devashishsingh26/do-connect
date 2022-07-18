import { Answer } from './Answer';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AnswerService {

     url= "http://localhost:8080/api/";

   token = localStorage.getItem('token');
   userId = localStorage.getItem('id')
   username = localStorage.getItem('username')
    headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': `Bearer ${this.token}`
  })

  constructor(private http:HttpClient) { }

  postAnswer(answer:Answer,id:string|null) {
    return this.http.post(this.url+"answer/"+id+"/"+this.username,answer,{headers:this.headers});
  }

  deleteAnswer(id:number) {
    return this.http.delete(this.url+"answer/"+id,{headers:this.headers})
  }

  approveAnswer(id:number) {
    return this.http.put(this.url+"answer-approve/"+id,null,{headers:this.headers})

  }
}
