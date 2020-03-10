import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {Client} from '../model/client.model';
import {catchError, tap} from 'rxjs/operators';
import {ResultClient} from '../model/result-client.model';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

const apiUrl = 'http://192.168.15.21:8080/pessoas';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  constructor(private http: HttpClient) { }

  getClientes(client: Client): Observable<ResultClient> {
    return this.http.post<ResultClient>(apiUrl, client, httpOptions).pipe(
      tap(resultado => console.log(resultado)),
      catchError(this.handleError<ResultClient>(`getProdutos`))
    );
  }

  // getCliente(id: number): Observable<ResultClient> {
  //   const url = `${apiUrl}/${id}`;
  //   return this.http.get<ResultClient>(url).pipe(
  //     tap(_ => console.log(`leu o produto id=${id}`)),
  //     catchError(this.handleError<ResultClient>(`getProduto id=${id}`))
  //   );
  // }

  addCliente(client): Observable<Client> {
    const url = `${apiUrl}/criar`;
    return this.http.post<Client>(url, client, httpOptions).pipe(
      // tslint:disable-next-line:no-shadowed-variable
      tap((client: Client) => console.log(`adicionou o produto com w/ id=${client.id}`)),
      catchError(this.handleError<Client>('addProduto'))
    );
  }

  updateCliente(produto): Observable<any> {
    return this.http.put(apiUrl, produto, httpOptions).pipe(
      tap(_ => console.log(`atualiza o produco com id=`)),
      catchError(this.handleError<any>('updateProduto'))
    );
  }

  deleteCliente(id): Observable<Client> {
    const urlId = `${apiUrl}/${id}`;
    return this.http.delete<Client>(urlId, httpOptions).pipe(
      tap(resultado => console.log(`resultado: ${resultado}`)),
      catchError(this.handleError<Client>('deleteProduto'))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error);

      return of(result as T);
    };
  }
}
