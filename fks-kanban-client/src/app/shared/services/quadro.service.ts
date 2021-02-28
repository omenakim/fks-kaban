import { HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { take } from 'rxjs/operators';
import { AuthHttp } from 'src/app/core/http/auth-http';
import { environment } from 'src/environments/environment';
import { CriarQuadroRequest, Quadro, QuadroSumario } from '../models/quadro';
import { Usuario } from '../models/usuario';

@Injectable({
  providedIn: 'root'
})
export class QuadroService {

  private apiURL = `${environment.baseURL}/quadros`;

  constructor(private http: AuthHttp) { }

  save(criarQuadroRequest: CriarQuadroRequest): Observable<void> {
    return this.http.post<void>(`${this.apiURL}`, criarQuadroRequest, { withCredentials: true }).pipe(take(1));
  }

  listarQuadrosDoUsuarioLogado(page: number, size: number): Observable<QuadroSumario[]> {

    let params = new HttpParams({
      fromObject: {
        page: page.toString(),
        size: size.toString(),
      }
    });

    return this.http.get(`${this.apiURL}`, { params });
  }

  buscarPorId(id: number): Observable<Quadro> {
    return this.http.get(`${this.apiURL}/${id}`);
  }

  findNaoMembrosByQuadroId(quadroId: number): Observable<Usuario[]> {
    return this.http.get(`${this.apiURL}/${quadroId}/nao-membros`);
  }

}
