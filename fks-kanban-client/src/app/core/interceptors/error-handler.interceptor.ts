import {
  HttpEvent,
  HttpInterceptor,
  HttpHandler,
  HttpRequest,
  HttpErrorResponse
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthService } from '../services/auth.service';
import { MessageService } from '../services/message.service';


@Injectable()
export class ErrorHandlerInterceptor implements HttpInterceptor {

  constructor(
    private router: Router,
    private messageService: MessageService,
    private authService: AuthService
  ) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    let messages = [];
    let type = 'success';

    return next.handle(request)
      .pipe(
        retry(1),
        catchError((response: HttpErrorResponse) => {
          const statusCode = response.status;
          console.log(response.error)
          if (statusCode < 499) {
            if (statusCode === 401) {
              this.authService.cleanAccessToken();
              this.router.navigate(['/login']);
              messages.push('Sessão expirada');
              type = 'warning';
            }
            else if (statusCode === 404) {
              this.router.navigate(['/not-found']);
            }
            else if (statusCode === undefined || statusCode === 0) {
              messages.push('Serviço indisponível');
              type = 'danger';
            } else {
              type = 'warning';
              if (response.error.errors) {
                response.error.errors.forEach(detail => {
                  if (detail)
                    messages.push(detail.userMessage);
                });
              } else {
                if (response.error.title)
                  messages.push(response.error.title);
              }
            }

          } else {
            messages.push('Erro do lado do servidor');
            type = 'danger';
          }
          this.messageService.showMessage(messages, type);
          return throwError('Erro ao consultar a api');
        })
      );
  }


}
