import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CreditSearchComponent } from './credits/credit-search/credit-search.component';

@Component({
    selector: 'app-root',
    template: `
    <div class="app-container">
      <h1>Minha aplicação de Créditos</h1>
      <app-credit-search></app-credit-search>
    </div>
  `,
    styles: [`
    .app-container {
      max-width: 1200px;
      margin: 0 auto;
      padding: 20px;
    }
    
    h1 { 
      text-align: center;
      margin: 20px 0;
      color: #2c3e50;
      font-size: 2em;
      font-weight: 600;
      text-transform: uppercase;
      letter-spacing: 1px;
    }
  `],
    imports: [RouterOutlet, CreditSearchComponent]
})
export class App { }
