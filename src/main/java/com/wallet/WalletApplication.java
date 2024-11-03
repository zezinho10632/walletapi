package com.wallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.repository")
@EntityScan(basePackages = "entity")
@ComponentScan(basePackages = {
		"com.wallet",
		"Service",
		"Service.Impl",
		"controller",
		"Controller",
		"SecurityConfiguration.security"  // Adicionando o pacote de segurança
})
public class WalletApplication {

	public static void main(String[] args) {
		SpringApplication.run(WalletApplication.class, args);
	}
}