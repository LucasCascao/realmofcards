package br.com.cascao.realmofcard.schedule;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.apachecommons.CommonsLog;

@Component
@EnableScheduling
public class EmailSchedule {
	
	private final long SEGUNDO = 1000;
    private final long MINUTO = SEGUNDO * 60; 
    private final long HORA = MINUTO * 60;
    
    @Scheduled(fixedDelay = SEGUNDO * 10) 
    public void verificaPorHora() { 
        System.out.println("Teste");
    }

}
