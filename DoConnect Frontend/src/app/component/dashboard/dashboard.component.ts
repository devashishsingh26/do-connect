import { Question } from './Question';
import { QuestionsService } from './questions.service';
import { Component, OnInit } from '@angular/core';
import { UntypedFormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

   emailFormControl = new UntypedFormControl('', [Validators.required, Validators.email]);

  // matcher = new MyErrorStateMatcher();
  ISSUES_LIST = [
  { name: 'To Do', statusValue: 1, issues: [] },
  { name: 'Development', statusValue: 2, issues: [] },
  { name: 'Testing', statusValue: 3, issues: [] },
  { name: 'Completed', statusValue: 4, issues: [] }
];
  // issues: { name: string; statusValue: number; issues: any[] }[] =
  //   Array.from(this.ISSUES_LIST);

  STATUS_MAPPER: Record<number, string> = {
  1: 'To-Do',
  2: 'Development',
  3: 'Testing',
  4: 'Completed'
};

    role = localStorage.getItem('role')
    question: Question = new Question();
    updatequestion:Question = new Question();
    isUpdate = false;

    roleADMIN() {
      return this.role === "ADMIN";
    }

    askQuestoin() {
      if(this.question.question.trim().length>0) {
        console.log(this.question);
        this.questionService.askQuestion(this.question).subscribe (data => {
          console.log(data);
        })
      }
    }

    approveQuestion(id:number) {
      
      this.questionService.approveQuestion(id).subscribe(data => {
        console.log(data)
        alert("Question is approved");
      })
      console.log(id)

    }

    deleteQuestion(id:number) {
      this.questionService.deleteQuestion(id).subscribe(data => {
        console.log(data)
      })
    }
    updateQuestionStatus(id:number) {
      this.isUpdate = true;
      localStorage.setItem('questionId',id.toString())
      console.log(this.updatequestion.question)

    }

    updateQuestion() {
      console.log(this.updatequestion)
      const id:string |null= localStorage.getItem('questionId');
      this.questionService.updateQuestion(id,this.updatequestion).subscribe(data => {
        console.log(data)
      })
       this.isUpdate = false;
    }

  constructor(
    private questionService:QuestionsService
  ) {    
        for (let issueType of this.questionList) {
      // issueType.issues = [];

    }}

  ngOnInit(): void {
    this.questions();

  }
questionList: { name: string; statusValue: number; issues: any[] }[] =
    Array.from(this.ISSUES_LIST);

    questions() {
      let questions:Question[] = [];
      this.questionService.getAllQuestion().subscribe(
      
      (data:Question[]) => {
        
        let i = 1;
        data.forEach(q => {
          
          q.statusValue =  i;
          i+=1;
          if(i==5)
          i=1;
          // console.log(q);
          questions.push(q);
        })
console.log(questions)
 for (let issueType of this.questionList) {
      issueType.issues = [];
      
    }

      questions.filter(q => {
          Object.keys(this.STATUS_MAPPER).forEach((statusCode) => {
            if (Number(statusCode) === q.statusValue) {
                this.questionList.forEach(ques => {
                  if(ques.statusValue === Number(statusCode)) {
                    ques.issues.push(q);
                  }
                })
            } 
          }
      )})

      this.questionList.forEach(q => {console.log(q.issues)})
      console.log("role "+this.role)

        
      }
    )
    
    }
 
}
