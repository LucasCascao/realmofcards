package br.com.cascao.realmofcard.web.filtro.pessoa;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import br.com.cascao.realmofcard.dao.PessoaDAO;
import br.com.cascao.realmofcard.domain.Pessoa;

/**
 * Servlet Filter implementation class GetPessoa
 */
@WebFilter("/alterarPessoa.jsp")
public class FiltroAlterarPessoa implements Filter {

    /**
     * Default constructor. 
     */
    public FiltroAlterarPessoa() {}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//Fazendo cast da ServletRequest para HttpServletRequest
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		int idEnviado = Integer.parseInt(request.getParameter("id"));
		
		Pessoa pessoa = (Pessoa) httpRequest.getSession().getAttribute("pessoa");
		
		//Verificando se não há nenhum objeto mapeado para a chave 'pessoa' e com o mesmo id
		if(pessoa == null || pessoa.getId() != idEnviado) {
			pessoa = new Pessoa();
			pessoa.setId(idEnviado);
			new PessoaDAO().consultar(pessoa);
			httpRequest.getSession().setAttribute("pessoa", pessoa);
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

}
