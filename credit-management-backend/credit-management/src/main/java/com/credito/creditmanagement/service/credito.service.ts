import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Credito {
  id?: number; // opcional
  numero: string;
  valor: number;
}

@Injectable({
  providedIn: 'root'
})
export class CreditoService {
  private apiUrl = 'http://localhost:8080/api/creditos';

  constructor(private http: HttpClient) {}

  // Busca todos os créditos
  getCredits(): Observable<Credito[]> {
    return this.http.get<Credito[]>(this.apiUrl);
  }

  // Busca por NFSe
  getCreditsByNfse(nfse: string): Observable<Credito[]> {
    return this.http.get<Credito[]>(`${this.apiUrl}/nfse/${nfse}`);
  }

  // Busca por número de crédito
  getCreditByNumber(numero: string): Observable<Credito | null> {
    return this.http.get<Credito>(`${this.apiUrl}/numero/${numero}`);
  }

  // Cria um novo crédito
  createCredito(credito: Credito): Observable<Credito> {
    return this.http.post<Credito>(this.apiUrl, credito);
  }
}
