package com.mantinha.observatoriodepasta;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.mantinha.observatoriodepasta.watcher.Sentinel;

@SpringBootApplication
public class ObservatorioDePastaApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		Sentinel sentinel = new Sentinel();
		do {
			ConfigurableApplicationContext context = SpringApplication.run(ObservatorioDePastaApplication.class, args);
			context.close();			
			sentinel.watcher();			
		}while(sentinel.state() == true);
		main(args);
	}

}
