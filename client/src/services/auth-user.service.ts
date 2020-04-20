import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Usuario} from '../model/domain/usuario.model';
import {Observable} from 'rxjs';
import {API_URL} from '../app/shared/app.api';
import {ResultUser} from '../model/results/result-user.model';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};


@Injectable({
  providedIn: 'root'
})

// @ts-ignore
export class AuthUserService {

  constructor(private httpClient: HttpClient) { }

  autenticar(user: Usuario): Observable<ResultUser> {
    return this.httpClient.post<ResultUser>(`${API_URL}/autenticar`, user, httpOptions);
  }
}
