import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthHttp } from 'src/app/core/http/auth-http';
import { environment } from 'src/environments/environment';
import { QuadroSumario } from '../models/quadro';

@Injectable({
  providedIn: 'root'
})
export class QuadroService {

  private apiURL = `${environment.baseURL}/quadros`;

  constructor(private http: AuthHttp) { }

  findAllThatUserBelongs(): Observable<QuadroSumario[]> {
    return this.http.get(`${this.apiURL}`);
  }

}
