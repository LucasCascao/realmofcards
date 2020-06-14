import { Dashboard } from './../../../model/domain/grafico/dashboard';
import { UtilService } from './../../../services/util.service';
import { Component, OnInit } from '@angular/core';
import { Chart } from 'angular-highcharts';
import { Options } from 'highcharts';
import * as _ from 'lodash';
import { Util } from 'src/app/shared/app.util';

const regexData = '((0?[1-9]|[12]\d|3[01])[\.\-\/](0?[13578]|10|12)[\.\-\/](19[7-9]\d|2[0-2][0-9]\d))';

@Component({
  selector: 'app-analyze',
  templateUrl: './analyze.component.html',
  styleUrls: ['./analyze.component.css']
})
export class AnalyzeComponent implements OnInit {

  options: Options;
  dashboard: Dashboard;
  dataInicio: string;
  dataFim: string;
  mensagemErro: Array<string>;
  expressaoRegularData = '';


  chart: Chart;

  constructor(private service: UtilService,
              private util: Util) {}

              ngOnInit() {
                this.mensagemErro = [];
  }

  formatarData(data: string, nomeCampoData: string): string {
    if (data !== undefined && data != null && data !== '') {
      const dataFormatada: string[] = data.split('/');
      if (dataFormatada.length === 3) {
        if (this.validaDigito(dataFormatada[0], 2, 31)
          && this.validaDigito(dataFormatada[1], 2, 12)
          && this.validaDigito(dataFormatada[2], 4, 2020)) {

          const dia = dataFormatada[0];
          const mes = dataFormatada[1];
          const ano = dataFormatada[2];
          return ano + '-' + mes + '-' + dia;
        } else {
          this.mensagemErro.push('Formato da data é dd/mm/aaaa');
        }
      } else {
        this.mensagemErro.push('Formato da data é dd/mm/aaaa');
      }
    } else {
      this.mensagemErro.push('O campo ' + nomeCampoData + ' é obrigatório(a)');
    }
    return null;
  }

  validaDigito(digito: string, quantidade: number, maximo: number): boolean{
    const digitoConvertido = Number.parseInt(digito);
    return this.validaQuantidade(digito, quantidade) && (digitoConvertido > 0 && digitoConvertido <= maximo) ? true : false;
  }

  validaQuantidade(valor: string, quantidade: number): boolean {
    return valor.length === quantidade ? true : false;
  }

  getDadosParaConstruirDashboard() {
    this.mensagemErro = [];
    const dataInicioFormatada = this.formatarData(this.dataInicio, 'data de inicio');
    const dataFimFormatada = this.formatarData(this.dataFim, 'data de fim');
    if (dataInicioFormatada != null && dataFimFormatada != null) {
      this.dashboard.dataInicio = new Date(dataInicioFormatada);
      this.dashboard.dataFim = new Date(dataFimFormatada);
      this.service.get(this.dashboard, 'dashboard').subscribe( resultado => {
        if (resultado?.msg == null) {
          this.dashboard = resultado?.entidades[0];
          this.montarDashboard();
        } else {
          this.mensagemErro = this.util.getMensagensSeparadas2(resultado?.msg);
        }
      });
    }
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
    const mesesAnalisados = this.dashboard.series[0];
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
