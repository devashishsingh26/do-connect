import { Answer } from './../dashboard/Answer';
import { AnswerService } from './../dashboard/answer.service';
import { Question } from './../dashboard/Question';
import { Component, Input, OnInit } from '@angular/core';

import { PRIORITY_MAPPER } from 'src/app/global.constants';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.scss']
})
export class CardComponent {
  @Input() content!: any;
  priority_mapper = PRIORITY_MAPPER;

  giveanswer:boolean = false;
  role = localStorage.getItem('role')

    roleADMIN() {
      return this.role === "ADMIN";
    }
  giveAnswer(id:number) {
    localStorage.setItem('quesIdForAnswer',id.toString())
    this.giveanswer = !this.giveanswer;
  }
  deleteAnswer(id:number) {
    console.log(id)
    this.answerService.deleteAnswer(id).subscribe(data => {
      console.log(data)
    });
  }

  approveAnswer(id:number) {
    this.answerService.approveAnswer(id).subscribe(data => {
      console.log(data)
    });
  }
  answer:Answer = new Answer();
  constructor(private answerService:AnswerService) {

  }

  postAnswer() {
   const quesIdForAnswer= localStorage.getItem('quesIdForAnswer');
   console.log(this.answer)
   this.answerService.postAnswer(this.answer,quesIdForAnswer).subscribe(data=>{
    console.log(data)
    this.giveanswer = !this.giveanswer;
   })
  }
  
}

