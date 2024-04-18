import { Component } from '@angular/core';

@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrl: './reports.component.css'
})


export class ReportsComponent {
  showReport: boolean = false;
  showReport2: boolean = false;

  toggleReport() {
    this.showReport = !this.showReport;
    
}
toggleReport02() {
  this.showReport2 = !this.showReport2; 
}
}