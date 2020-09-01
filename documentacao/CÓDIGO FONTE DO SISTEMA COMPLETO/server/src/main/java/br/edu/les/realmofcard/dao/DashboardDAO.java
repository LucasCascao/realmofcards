package br.edu.les.realmofcard.dao;

import br.edu.les.realmofcard.domain.*;
import br.edu.les.realmofcard.domain.grafico.Dashboard;
import br.edu.les.realmofcard.domain.grafico.Serie;
import br.edu.les.realmofcard.repository.BandeiraRepository;
import br.edu.les.realmofcard.repository.CategoriaCartaRepository;
import br.edu.les.realmofcard.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardDAO implements IDAO {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private CategoriaCartaRepository categoriaCartaRepository;

    private Map<Integer, String> mapaMesesTraduzidos;

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) { return null; }

    @Override
    public void alterar(EntidadeDominio entidade) {}

    @Override
    public void excluir(EntidadeDominio entidade) {}

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {

        List<EntidadeDominio> dashboardList = new ArrayList<>();
        Dashboard dashboard = (Dashboard) entidade;

        mapaMesesTraduzidos = new HashMap<>();

        mapaMesesTraduzidos.put(1, "Janeiro");
        mapaMesesTraduzidos.put(2, "Fevereiro");
        mapaMesesTraduzidos.put(3, "Março");
        mapaMesesTraduzidos.put(4, "Abril");
        mapaMesesTraduzidos.put(5, "Maio");
        mapaMesesTraduzidos.put(6, "Junho");
        mapaMesesTraduzidos.put(7, "Julho");
        mapaMesesTraduzidos.put(8, "Agosto");
        mapaMesesTraduzidos.put(9, "Setembro");
        mapaMesesTraduzidos.put(10, "Outubro");
        mapaMesesTraduzidos.put(11, "Novembro");
        mapaMesesTraduzidos.put(12, "Dezembro");

        if(dashboard.getTipoGrafico().trim().toUpperCase().equals("ANTIGO")) {

            dashboard.setTitle("Gráfico de Vendas");

            List<Pedido> pedidosFiltrados = pedidoRepository.getDataToSellDashboard(dashboard.getDataInicio(), dashboard.getDataFim());

            dashboard.setSeries(new ArrayList<>());

            Serie serieValorVenda = new Serie();
            serieValorVenda.setName("Subtotal de Vendas por Mês");
            serieValorVenda.setData(new ArrayList<>());

            Serie serieQuantidadeVenda = new Serie();
            serieQuantidadeVenda.setName("Quantidade de Vendas por Mês");
            serieQuantidadeVenda.setData(new ArrayList<>());

            Serie serieMeses = new Serie();
            serieMeses.setName("Meses Análisados");
            serieMeses.setData(new ArrayList<>());

            Double valorTotalDoMes = 0.0;
            Integer valorQuantidadeVenda = 0;

            DecimalFormat df = new DecimalFormat("#####0.00");

            LocalDate ultimaDataAnalisada = pedidosFiltrados.get(0).getDataCompra();
            serieMeses.getData().add(mapaMesesTraduzidos.get(pedidosFiltrados.get(0).getDataCompra().getMonthValue()) + " / " + pedidosFiltrados.get(0).getDataCompra().getYear());
            for (Pedido pedidoFiltrado : pedidosFiltrados) {
                LocalDate dataAtualParaAnalise = pedidoFiltrado.getDataCompra();
                if(dataAtualParaAnalise.getMonthValue() != ultimaDataAnalisada.getMonthValue()){

                    serieValorVenda.getData().add(Double.valueOf(df.format(valorTotalDoMes).replace(",",".")));
                    serieMeses.getData().add(mapaMesesTraduzidos.get(pedidoFiltrado.getDataCompra().getMonthValue()) + " / " + pedidoFiltrado.getDataCompra().getYear());
                    serieQuantidadeVenda.getData().add(valorQuantidadeVenda);

                    valorTotalDoMes = 0.0;
                    valorQuantidadeVenda = 0;
                }
                valorTotalDoMes += pedidoFiltrado.getValorTotal();
                valorQuantidadeVenda += 1;
                ultimaDataAnalisada = dataAtualParaAnalise;
            }

            serieValorVenda.getData().add(Double.valueOf(df.format(valorTotalDoMes).replace(",",".")));
            serieQuantidadeVenda.getData().add(valorQuantidadeVenda);

            dashboard.getSeries().add(serieMeses);
            dashboard.getSeries().add(serieValorVenda);
            dashboard.getSeries().add(serieQuantidadeVenda);

            dashboardList.add(dashboard);
        }

        if(dashboard.getTipoGrafico().trim().toUpperCase().equals("VENDAPORCATEGORIA")){

            dashboard.setTitle("Vendas Mensais por Categoria");

            List<Pedido> pedidosFiltrados = pedidoRepository.getDataToSellDashboard(dashboard.getDataInicio(), dashboard.getDataFim());

            List<CategoriaCarta> categorias = (List<CategoriaCarta>) categoriaCartaRepository.findAll();

            dashboard.setSeries(new ArrayList<>());

            Serie serieMeses = new Serie();
            serieMeses.setName("Meses Análisados");
            serieMeses.setData(new ArrayList<>());

            Map<String, Serie> mapaCategoriaSerie = new HashMap<>();
            Map<String, Integer> mapaCategoriaContadorQuantidade = new HashMap<>();

            for (CategoriaCarta categoria : categorias) {
                Serie serie = new Serie();
                serie.setName(categoria.getNome());
                serie.setData(new ArrayList<>());
                mapaCategoriaContadorQuantidade.put(categoria.getNome(), 0);
                mapaCategoriaSerie.put(categoria.getNome(), serie);
            }

            LocalDate ultimaDataAnalisada = pedidosFiltrados.get(0).getDataCompra();
            serieMeses.getData().add(mapaMesesTraduzidos.get(pedidosFiltrados.get(0).getDataCompra().getMonthValue()) + " / " + pedidosFiltrados.get(0).getDataCompra().getYear());
            for (Pedido pedidoFiltrado : pedidosFiltrados) {
                LocalDate dataAtualParaAnalise = pedidoFiltrado.getDataCompra();
                if(dataAtualParaAnalise.getMonthValue() != ultimaDataAnalisada.getMonthValue()){
                    serieMeses.getData().add(mapaMesesTraduzidos.get(pedidoFiltrado.getDataCompra().getMonthValue()) + " / " + pedidoFiltrado.getDataCompra().getYear());
                    mapaCategoriaSerie.forEach((nomeCategoria, serie) -> {
                        serie.getData().add(mapaCategoriaContadorQuantidade.get(nomeCategoria));
                        mapaCategoriaContadorQuantidade.put(nomeCategoria, 0);
                    });
                }
                for (Item item : pedidoFiltrado.getItemList()) {
                    Integer quantidadeAtual = mapaCategoriaContadorQuantidade.get(item.getCarta().getCategoriaCarta().getNome());
                    mapaCategoriaContadorQuantidade.put(item.getCarta().getCategoriaCarta().getNome(), ++quantidadeAtual);
                }
                ultimaDataAnalisada = dataAtualParaAnalise;
            }

            dashboard.getSeries().add(serieMeses);

            mapaCategoriaSerie.forEach((nomeCategoria, serie) -> {
                serie.getData().add(mapaCategoriaContadorQuantidade.get(nomeCategoria));
                mapaCategoriaContadorQuantidade.put(nomeCategoria, 0);
                dashboard.getSeries().add(serie);
            });

            dashboardList.add(dashboard);
        }
        return dashboardList;
    }
}
