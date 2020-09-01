package br.edu.les.realmofcard.util;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class MovedorImagem {

    public static Boolean existeImagem(String imagemPath){
        return new File(imagemPath).exists();
    }

    public static String mover(String imagemPath) throws IOException{
        // Arquivo a ser movido
        File arquivo = new File(imagemPath);

        // Diretorio de destino
        File diretorioDestino = new File(new File("./../client/src/assets/images/cartas").getCanonicalPath());

        // Move o arquivo para o novo diretorio
        arquivo.renameTo(new File(diretorioDestino, arquivo.getName()));

        return "/assets/images/cartas/" + arquivo.getName();
    }
}
