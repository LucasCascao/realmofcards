package br.com.cascao.realmofcard.web.filtro.pessoa;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import br.com.cascao.realmofcard.dao.PessoaDAO;
import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Pessoa;

/**
 * Servlet Filter implementation class FiltroListarPessoa
 */
@WebFilter("/index.jsp")
public class FiltroListarPessoa implements Filter {

    /**
     * Default constructor. 
     */
    public FiltroListarPessoa() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		System.out.println("Entrou filtro");
		
		//Verificando se não há nenhum objeto mapeado para a chave 'pessoas' na sessão
//		if(httpRequest.getSession().getAttribute("pessoas") == null) {
			List<EntidadeDominio> pessoas =  new PessoaDAO().consultar(new Pessoa());
			httpRequest.getSession().setAttribute("pessoas", pessoas);
//		}
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
