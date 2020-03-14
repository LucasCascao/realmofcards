import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Person} from '../model/person.model';
import {Observable} from 'rxjs';
import {ResultClient} from '../model/result-person.model';
import {API_URL, HTTP_OPTIONS} from '../app/shared/app.api';
import {ResultUser} from '../model/result-user.model';
import {User} from '../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(private http: HttpClient) { }

  getClientes(user: User): Observable<ResultUser> {
    return this.http.post<ResultUser>(`${API_URL}/usuarios`, user, HTTP_OPTIONS);
  }

  addUser(user: User): Observable<ResultUser> {
    return  this.http.post<ResultUser>(`${API_URL}/usuarios/cria`, user, HTTP_OPTIONS);
  }

  updateCliente(user: User): Observable<ResultUser> {
    return this.http.put<ResultUser>(`${API_URL}/usuarios/`, user, HTTP_OPTIONS);
  }

  deleteCliente(user: User): Observable<ResultUser> {
    return this.http.delete<ResultUser>(`${API_URL}/usuarios/${user.id}`, HTTP_OPTIONS);
  }

}
