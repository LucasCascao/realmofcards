import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-app-logado',
  templateUrl: './app-logado.component.html',
  styleUrls: ['./app-logado.component.css']
})
export class AppLogadoComponent implements OnInit {

  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
  }

}
