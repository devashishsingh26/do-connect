import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Login } from './Login';
import { LoginResponse } from './LoginResponse';
import { Signup } from './Signup';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  url= "http://localhost:8080/api/";

  constructor(private http:HttpClient) {
    
   }

   loginUser(user:Login):any {

    return this.http.post<LoginResponse>(this.url+"login",user);
      
    

   }

   signupUser(user:Signup):any {
    return this.http.post(this.url+"signup",user);
   }
  
}
