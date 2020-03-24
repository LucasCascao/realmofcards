import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {Person} from '../model/domain/person.model';
import {ResultClient} from '../model/results/result-person.model';

import {API_URL, HTTP_OPTIONS} from '../app/shared/app.api';

@Injectable({
  providedIn: 'root'
})

export class ClienteService {

  constructor(private http: HttpClient) { }

  getClientes(person: Person): Observable<ResultClient> {
    return this.http.post<ResultClient>(`${API_URL}/pessoas`, person, HTTP_OPTIONS);
  }

  addCliente(person: Person): Observable<ResultClient> {
    return  this.http.post<ResultClient>(`${API_URL}/pessoas/cria`, person);
  }

  updateCliente(person): Observable<ResultClient> {
    return this.http.put<ResultClient>(`${API_URL}/pessoas`, person);
  }

  deleteCliente(person: Person): Observable<ResultClient> {
    return this.http.delete<ResultClient>(`${API_URL}/pessoas/${person.id}`);
  }
}
