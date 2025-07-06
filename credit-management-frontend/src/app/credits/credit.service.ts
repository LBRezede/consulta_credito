import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CreditService {
  private apiUrl = 'http://localhost:8080/api/creditos';

  constructor(private http: HttpClient) { }

  getCreditsByNfse(numeroNfse: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/${numeroNfse}`);
  }

  getCreditByNumber(numeroCredito: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/credito/${numeroCredito}`);
  }
}

