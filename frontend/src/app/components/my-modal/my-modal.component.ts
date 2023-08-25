import { Component, EventEmitter, Output, Input } from '@angular/core';

@Component({
  selector: 'app-my-modal',
  templateUrl: './my-modal.component.html',
  styleUrls: ['./my-modal.component.scss']
})
export class MyModalComponent {
  @Input() show = false;
  @Output() close = new EventEmitter<void>();

  onClose() {
    this.close.emit();
  }
}

