@Injectable({
  providedIn: 'root'
})
export class CreditService {
  private apiUrl = 'http://localhost:8081/api/creditos';

  constructor(private http: HttpClient) { }

  getCreditsByNfse(numeroNfse: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/${numeroNfse}`);
  }

  getCreditByNumber(numeroCredito: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/credito/${numeroCredito}`);
  }
}