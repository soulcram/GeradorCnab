package br.com.m3Tech.solucoesFromtis.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@EnableJpaRepositories
@EntityScan(basePackages = "br.com.m3Tech.solucoesFromtis.model")
public class ConfigBean {


}
