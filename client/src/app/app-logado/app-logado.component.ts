import { Component, OnInit } from '@angular/core';
import { UtilService } from 'src/services/util.service';
@Component({
  selector: 'app-app-logado',
  templateUrl: './app-logado.component.html',
  styleUrls: ['./app-logado.component.css']
})
export class AppLogadoComponent implements OnInit {

  constructor(private service: UtilService) { }

  ngOnInit(): void {}
}
