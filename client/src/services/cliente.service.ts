import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {Person} from '../model/person.model';
import {catchError, tap} from 'rxjs/operators';
import {ResultClient} from '../model/result-person.model';

import {API_URL} from '../app/shared/app.api';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

const apiUrl = 'http://localhost:8080/pessoas';

@Injectable({
  providedIn: 'root'
})

export class ClienteService {

  constructor(private http: HttpClient) { }

  getClientes(person: Person): Observable<ResultClient> {
    return this.http.post<ResultClient>(`${API_URL}/pessoas`, person, httpOptions);
  }

  addCliente(person: Person): Observable<ResultClient> {
    return  this.http.post<ResultClient>(`${API_URL}/pessoas/cria`, person);
  }

  updateCliente(person): Observable<ResultClient> {
    return this.http.put<ResultClient>(`${API_URL}/pessoas/`, person);
  }

  deleteCliente(person: Person): Observable<ResultClient> {
    return this.http.delete<ResultClient>(`${API_URL}/pessoas/${person.id}`);
  }
}
