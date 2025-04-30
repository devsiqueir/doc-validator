package br.com.siqueiradev.doc_validator;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Application {

	public static void main(String[] args) {
		// Carrega variáveis do .env como System properties
		Dotenv dotenv = Dotenv.configure()
				.ignoreIfMissing() // não quebra se não existir
				.load();

		dotenv.entries().forEach(entry ->
				System.setProperty(entry.getKey(), entry.getValue())
		);

		SpringApplication.run(Application.class, args);
	}
}