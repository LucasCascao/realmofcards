import {Component, OnInit} from '@angular/core';
import {Service} from './service';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'realm-of-card';
  pessoas$: Observable<any>;

  pessoas: Array<any>;

  constructor(private service: Service) {
  }
  ngOnInit(): void {
    // tslint:disable-next-line:no-unused-expression
    this.pessoas$ = this.service.pessoas;
    this.pessoas$.subscribe(pessoa => {
      this.pessoas.push(pessoa);
    });
  }
}
