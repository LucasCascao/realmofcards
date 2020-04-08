import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { API_URL, HTTP_OPTIONS } from 'src/app/shared/app.api';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UtilService {

  constructor(private http: HttpClient) { }

  get(objeto: any, servico: string): Observable<any> {
    return this.http.post<any>(`${API_URL}/${servico}`, objeto, HTTP_OPTIONS);
  }

  add(objeto: any, servico: string): Observable<any> {
    return  this.http.post<any>(`${API_URL}/${servico}/cria`, objeto, HTTP_OPTIONS);
  }

  update(objeto: any, servico: string): Observable<any> {
    return this.http.put<any>(`${API_URL}/${servico}`, objeto, HTTP_OPTIONS);
  }

  delete(id: number, servico: string): Observable<any> {
    return this.http.delete<any>(`${API_URL}/${servico}/${id}`, HTTP_OPTIONS);
  }
}
