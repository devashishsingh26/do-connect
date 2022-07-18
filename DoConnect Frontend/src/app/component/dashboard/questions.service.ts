import { Question } from './Question';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class QuestionsService {

   url= "http://localhost:8080/api/";

   token = localStorage.getItem('token');
   userId = localStorage.getItem('id')
    headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': `Bearer ${this.token}`
  })

              
   
   

  constructor(private http:HttpClient) { }


  getAllQuestion():any {

    return this.http.get(this.url+"questions",{headers:this.headers})
  }

  askQuestion(question:Question) {
    console.log(this.userId)
    return this.http.post(this.url+"questions/"+this.userId,question,{headers:this.headers})
  }

  approveQuestion(id:number) {
    return this.http.put(this.url+"questions-approve/"+Number(id),null,{headers:this.headers})
  }

  deleteQuestion(id:number) {
    return this.http.delete(this.url+"questions/"+id,{headers:this.headers})
  }

  updateQuestion(id:string|null,questoin:Question) {
    return this.http.put(this.url+"questions/"+id,questoin,{headers:this.headers})
  }

}
