package br.com.cascao.realmofcard.endpoint;

import br.com.cascao.realmofcard.domain.CardBean;
import br.com.cascao.realmofcard.domain.TypeBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carta")
public class CardEndpoint {

    private CardBean cardBean;
    private TypeBean typeBean;

    @GetMapping()
    public CardBean getCard(){
        this.cardBean = new CardBean();
        this.typeBean = new TypeBean();

        this.typeBean.setNomeCategoria("Monstro");

        this.cardBean.setName("Mago Negro");
        this.cardBean.setType(typeBean);
        return this.cardBean;
    }

}
