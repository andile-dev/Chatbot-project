import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  query = '';
  intent: string | null = null;

  sendQuery() {
    if (!this.query.trim()) return;
    fetch('http://localhost:8082/api/intent', {
      method: 'POST',
      headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
      body: `query=${encodeURIComponent(this.query)}`
    })
    .then(res => res.json())
    .then(data => {
      this.intent = data.intent;
    })
    .catch(err => {
      console.error('Request failed:', err);
      this.intent = 'error';
    });
  }
}
