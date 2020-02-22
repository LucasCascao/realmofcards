import {HttpClient} from '@angular/common/http';
import {Observable} from "rxjs";

export class Service {
  urlBase = 'http://localhost:8080';

  constructor(private httpService: HttpClient) {}

  get pessoas(): Observable<Array<any>> {
   return this.httpService.get<Array<any>>(`${this.urlBase}/pessoas`);
  }
}
