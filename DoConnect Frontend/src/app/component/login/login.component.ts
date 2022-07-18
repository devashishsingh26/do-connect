import { Component, OnInit } from '@angular/core';
import { UntypedFormGroup, UntypedFormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from './login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  isLoggin = true;

  favoriteSeason: string = "";
  seasons: string[] = ['Winter', 'Spring', 'Summer', 'Autumn'];

  form = new UntypedFormGroup({
    username: new UntypedFormControl('', [Validators.required]),
    password: new UntypedFormControl('', [Validators.required]),
    role: new UntypedFormControl()
  });  

  constructor( 
    private loginService:LoginService,
    private router:Router
    ) {}

  ngOnInit(): void {
  }

  isLogginChange() {
    console.log("hi")
    this.isLoggin = !this.isLoggin;
  }

  login() {
    if (this.form.valid) {
      this.loginService.loginUser(this.form.value).subscribe(
        (data:any) => {
          console.log(data.id)
          localStorage.setItem('token',data.token)
          localStorage.setItem('username',data.username)
          localStorage.setItem('role',data.role)
          localStorage.setItem('id',data.id)
          console.log(data.token+" "+data.username+" ");
          this.router.navigate(['/dashborad']);
        },
        (        error: any) => {
            console.log(error.error)
            alert(error.error);
        }
      )
      this.form.reset();
            // console.log(this.form.value)
            
    }
    
  }


  signup() {
    if (this.form.valid) {
      console.log(this.form.value)
      this.loginService.signupUser(this.form.value).subscribe(
        (data:any )=> {
            console.log(data);
            alert("user created, Please login")
        },
        (        error: any) => {
            console.log(error.error)
            alert(error.error);
        }
      )
      this.form.reset();
    }
  }
}