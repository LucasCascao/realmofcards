import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {Client} from '../model/client.model';
import {catchError, tap} from 'rxjs/operators';
import {ResultClient} from '../model/result-client.model';

import {API_URL} from '../app/shared/app.app';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

const apiUrl = 'http://localhost:8080/pessoas';

@Injectable({
  providedIn: 'root'
})

export class ClienteService {

  constructor(private http: HttpClient) { }

  getClientes(client: Client): Observable<ResultClient> {
    return this.http.post<ResultClient>(`${API_URL}/pessoas`, client, httpOptions);
  }

  addCliente(client: Client, resultado: ResultClient): Observable<ResultClient> {
    return  this.http.post<ResultClient>(`${API_URL}/pessoas/cria`, client);
  }

  updateCliente(client): Observable<ResultClient> {
    return this.http.put<ResultClient>(`${ apiUrl }`, client);
  }

  deleteCliente(client: Client): Observable<ResultClient> {
    return this.http.delete<ResultClient>(`${API_URL}/pessoas/${client.id}`);
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error);

      return of(result as T);
    };
  }
}
