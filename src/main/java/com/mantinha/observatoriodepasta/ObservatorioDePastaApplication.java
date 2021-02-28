package com.mantinha.observatoriodepasta;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mantinha.observatoriodepasta.watcher.Sentinel;

@SpringBootApplication
public class ObservatorioDePastaApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(ObservatorioDePastaApplication.class, args);
		new Sentinel().watcher();
	}

}
