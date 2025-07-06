import { Routes } from '@angular/router';
import { CreditSearchComponent } from './credits/credit-search/credit-search.component';

export const routes: Routes = [
  { path: '', component: CreditSearchComponent },
  { path: '**', redirectTo: '' }
];
