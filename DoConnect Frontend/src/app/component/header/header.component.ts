import { Router } from '@angular/router';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {


  @Input() showSearchBar = true;

  username = localStorage. getItem('username');

  constructor(
    private router:Router
    ) {
      console.log(this.username)
    }
      ngOnInit(): void {
        
  }
    
  logOut() {
    // this.auth.logOut();
    localStorage.removeItem('username')
    localStorage.removeItem('role')
    localStorage.removeItem('token')
    this.router.navigate(['/login']);
  }
}
