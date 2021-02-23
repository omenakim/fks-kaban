import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { take } from 'rxjs/operators';
import { AuthHttp } from 'src/app/core/http/auth-http';
import { environment } from 'src/environments/environment';
import { CriarQuadroRequest, QuadroSumario } from '../models/quadro';

@Injectable({
  providedIn: 'root'
})
export class QuadroService {

  private apiURL = `${environment.baseURL}/quadros`;

  constructor(private http: AuthHttp) { }

  save(criarQuadroRequest: CriarQuadroRequest): Observable<void> {
    return this.http.post<void>(`${this.apiURL}`, criarQuadroRequest, { withCredentials: true }).pipe(take(1));
  }

  findAllThatUserBelongs(): Observable<QuadroSumario[]> {
    return this.http.get(`${this.apiURL}`);
  }

}
