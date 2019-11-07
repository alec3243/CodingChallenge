package com.codingchallenge.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.codingchallenge.repositories")
public class RepositoryConfig {

}
