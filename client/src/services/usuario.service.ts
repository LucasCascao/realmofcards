import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Pessoa} from '../model/domain/person.model';
import {Observable} from 'rxjs';
import {ResultClient} from '../model/results/result-person.model';
import {API_URL, HTTP_OPTIONS} from '../app/shared/app.api';
import {ResultUser} from '../model/results/result-user.model';
import {Usuario} from '../model/domain/user.model';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(private http: HttpClient) { }

  getUsers(user: Usuario): Observable<ResultUser> {
    return this.http.post<ResultUser>(`${API_URL}/usuarios`, user, HTTP_OPTIONS);
  }

  updateCliente(user: Usuario): Observable<ResultUser> {
    return this.http.put<ResultUser>(`${API_URL}/usuarios`, user, HTTP_OPTIONS);
  }

}
