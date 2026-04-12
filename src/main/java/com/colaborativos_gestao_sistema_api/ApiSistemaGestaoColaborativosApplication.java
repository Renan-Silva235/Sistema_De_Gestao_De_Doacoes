package com.colaborativos_gestao_sistema_api;

import com.colaborativos_gestao_sistema_api.config.DotenvConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiSistemaGestaoColaborativosApplication {

	public static void main(String[] args) {
		DotenvConfig.load();
		SpringApplication.run(ApiSistemaGestaoColaborativosApplication.class, args);
	}

}
