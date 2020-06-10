package br.edu.les.realmofcard.endpoint;

import br.edu.les.realmofcard.domain.Carta;
import br.edu.les.realmofcard.domain.Foto;
import br.edu.les.realmofcard.domain.Resultado;
import br.edu.les.realmofcard.domain.grafico.Dashboard;
import br.edu.les.realmofcard.dto.CartaDTO;
import br.edu.les.realmofcard.facade.Fachada;
import br.edu.les.realmofcard.util.DTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/dashboard")
public class DashboardEndpoint {
    
    @Autowired
    private Fachada fachada;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Resultado> consultar(@RequestBody Dashboard dashboard){
        return ResponseEntity.ok().body(fachada.consultar(dashboard));
    }
}
