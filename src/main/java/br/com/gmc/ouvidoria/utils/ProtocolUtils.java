package br.com.gmc.ouvidoria.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class ProtocolUtils {

    public static String generate() {
         // Gera um fator aleatório com 5 dígitos
        Random random = new Random();
        int randomFactor = random.nextInt(100000); // Gera um número aleatório entre 0 e 99999

        // Obtém a data e hora atual no formato desejado (yyyyMMddHHmm)
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        String dateTimePart = now.format(formatter);

        // Combina a data/hora com o fator aleatório
        String protocolo = dateTimePart + "-" + String.format("%05d", randomFactor); // Fator aleatório com 5 dígitos

        // Exibe o número do protocolo gerado
        System.out.println("Número do Protocolo: " + protocolo);

        return protocolo;
    }
    
}
