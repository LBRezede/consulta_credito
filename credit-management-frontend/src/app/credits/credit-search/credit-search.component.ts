import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CreditService } from '../credit.service';

interface Credit {
  numeroCredito: string;
  numeroNfse: string;
  data: string;
  valorIssqn: number;
}

@Component({
  selector: 'app-credit-search',
  templateUrl: './credit-search.component.html',
  styleUrls: ['./credit-search.component.scss'],
  standalone: true,
  imports: [CommonModule, FormsModule],
  providers: [CreditService]
})
export class CreditSearchComponent implements OnInit {
  numeroNfse: string = '';
  numeroCredito: string = '';
  creditos: Credit[] = [];
  error: string | null = null;

  constructor(private creditService: CreditService) {}

  ngOnInit(): void {
    console.log('CreditSearchComponent initialized');
  }

  buscarPorNfse(): void {
    if (this.numeroNfse.trim()) {
      this.error = null;
      this.creditService.getCreditsByNfse(this.numeroNfse).subscribe({
        next: (data: Credit[]) => {
          this.creditos = data;
          if (data.length === 0) {
            this.error = 'Nenhum crédito encontrado para este Nfse';
          }
        },
        error: (err: any) => {
          console.error('Error fetching credits by Nfse:', err);
          this.error = 'Erro ao buscar créditos. Por favor, tente novamente.';
        }
      });
    }
  }

  buscarPorCredito(): void {
    if (this.numeroCredito.trim()) {
      this.error = null;
      this.creditService.getCreditByNumber(this.numeroCredito).subscribe({
        next: (data: Credit | null) => {
          this.creditos = data ? [data] : [];
          if (!data) {
            this.error = 'Nenhum crédito encontrado com este número';
          }
        },
        error: (err: any) => {
          console.error('Error fetching credit by number:', err);
          this.error = 'Erro ao buscar crédito. Por favor, tente novamente.';
        }
      });
    }
  }
}
