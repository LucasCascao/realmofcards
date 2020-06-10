import { Dashboard } from './../../../model/domain/grafico/dashboard';
import { UtilService } from './../../../services/util.service';
import { Component, Optional, OnInit, ViewChild, ElementRef } from '@angular/core';
import { Chart } from 'angular-highcharts';
import { first } from 'rxjs/operators';
import { Options } from 'highcharts';
import * as _ from 'lodash';
import { HighchartsService } from './highcharts.service';
import { Util } from 'src/app/shared/app.util';

@Component({
  selector: 'app-analyze',
  templateUrl: './analyze.component.html',
  styleUrls: ['./analyze.component.css']
})
export class AnalyzeComponent implements OnInit {

  options: Options;
  dashboard: Dashboard;
  dataInicio: Date;
  dataFim: Date;
  mensagemErro: Array<string>;

  chart: Chart;

  constructor(private service: UtilService,
              private util: Util) {}

  ngOnInit() {
    this.mensagemErro = [];
  }

  getDadosParaConstruirDashboard() {
    this.mensagemErro = [];
    this.dashboard.dataInicio = this.dataInicio;
    this.dashboard.dataFim = this.dataFim;
    this.service.get(this.dashboard, 'dashboard').subscribe( resultado => {
      if (resultado?.msg == null) {
        this.dashboard = resultado?.entidades[0];
        this.montarDashboard();
      } else {
        this.mensagemErro = this.util.getMensagensSeparadas2(resultado?.msg);
      }
    });
  }

  getGraficoVendaPorCategoria(){
    this.dashboard = new Dashboard();
    this.dashboard.tipoGrafico = 'VENDAPORCATEGORIA';
    this.getDadosParaConstruirDashboard();
  }

  getGraficoAntigo(){
    this.dashboard = new Dashboard();
    this.dashboard.tipoGrafico = 'ANTIGO';
    this.getDadosParaConstruirDashboard();
  }

  montarDashboard() {
    let mesesAnalisados = this.dashboard.series[0];
    this.dashboard.series.splice(0, 1);
    this.chart = new Chart({
      chart: {
        type: 'line'
      },
      title: {
        text: this.dashboard.title
      },
      yAxis: {
        title: {
          text: 'Quantidade vendida'
        }
      },
      xAxis: {
        categories: mesesAnalisados.data
      },
      credits: {
        enabled: false
      },
      series: this.dashboard.series
    });
  }

  // myOptions = {
  //   chart: {
  //     type: 'bar'
  //   },
  //   title: {
  //     text: 'Stacked bar chart'
  //   },
  //   xAxis: {
  //     categories: ['Apples', 'Oranges', 'Pears', 'Grapes', 'Bananas']
  //   },
  //   yAxis: {
  //     min: 0,
  //     title: {
  //       text: 'Total fruit consumption'
  //     }
  //   },
  //   legend: {
  //     reversed: true
  //   },
  //   plotOptions: {
  //     series: {
  //       stacking: 'normal'
  //     }
  //   },
  //   series: [{
  //     name: 'John',
  //     data: [5, 3, 4, 7, 2]
  //   }, {
  //     name: 'Jane',
  //     data: [2, 2, 3, 2, 1]
  //   }, {
  //     name: 'Joe',
  //     data: [3, 4, 4, 2, 5]
  //   }]
  // };

  // @ViewChild('charts') public chartEl: ElementRef;

  // constructor(private highcharts: HighchartsService) { }
  // ngOnInit() {
  //   this.highcharts.createChart(this.chartEl.nativeElement, this.myOptions);
  // }


}
