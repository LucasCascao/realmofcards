package br.com.cascao.realmofcard.endpoint;

import br.com.cascao.realmofcard.dto.PessoaDTO;
import br.com.cascao.realmofcard.util.DTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.cascao.realmofcard.domain.Pessoa;
import br.com.cascao.realmofcard.domain.Resultado;
import br.com.cascao.realmofcard.negocio.fachada.Fachada;

@RestController
@CrossOrigin
@RequestMapping("/pessoas")
public class PessoaEndpoint {
    
    @Autowired
    private Fachada fachada;

    @Autowired
    private Resultado resultado;

    @Autowired
    private PessoaDTO pessoaDTO;

    @PostMapping()
    public ResponseEntity<Resultado> consultar(@RequestBody Pessoa pessoa){
        return ResponseEntity.ok().body(DTOUtil.tranfereParaDTO(fachada.consultar(pessoa), pessoaDTO));
    }

    @PostMapping(path = "/cria")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> salvar(@RequestBody Pessoa pessoa){
        return ResponseEntity.ok().body(DTOUtil.tranfereParaDTO(fachada.salvar(pessoa), pessoaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Pessoa pessoa = new Pessoa();
        pessoa.setId(id);
        return ResponseEntity.ok().body(DTOUtil.tranfereParaDTO(fachada.excluir(pessoa), pessoaDTO));
    }

    @PutMapping()
    public ResponseEntity<?> alterar(@RequestBody Pessoa pessoa){
        return ResponseEntity.ok().body(DTOUtil.tranfereParaDTO(fachada.alterar(pessoa), pessoaDTO));
    }
}
