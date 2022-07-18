import { Question } from './../dashboard/Question';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

export type buttonTypes = 'primary' | 'secondary';

@Component({
  selector: 'app-button',
  templateUrl: './button.component.html',
  styleUrls: ['./button.component.scss']
})
export class ButtonComponent {
  @Input() text = '';
  @Input() btnType: buttonTypes = 'primary';
  @Input() disabled = false;

  @Output() btnClick: EventEmitter<void> = new EventEmitter<void>();

  onBtnClick = (): void => {
    this.btnClick.emit();
  };


}
