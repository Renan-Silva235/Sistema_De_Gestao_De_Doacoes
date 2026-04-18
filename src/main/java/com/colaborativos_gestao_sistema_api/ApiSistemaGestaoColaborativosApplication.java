package com.colaborativos_gestao_sistema_api;

import com.colaborativos_gestao_sistema_api.config.DotenvConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ApiSistemaGestaoColaborativosApplication {

	public static void main(String[] args) {
		DotenvConfig.load();
		SpringApplication.run(ApiSistemaGestaoColaborativosApplication.class, args);
	}

}
