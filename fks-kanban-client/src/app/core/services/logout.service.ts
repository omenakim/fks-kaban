import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { AuthHttp } from '../http/auth-http';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class LogoutService {

  tokensRenokeUrl: string;

  constructor(
    private http: AuthHttp,
    private auth: AuthService
  ) {
    this.tokensRenokeUrl = `${environment.baseURL}/auth/token/revoke`;
  }

  logout() {
    return this.http.delete(this.tokensRenokeUrl, { withCredentials: true })
      .toPromise()
      .then(() => {
        this.auth.cleanAccessToken();
      });
  }

}
