package com.mangel.startcms;

import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.env.Environment;

@EnableCaching
@SpringBootApplication
public class StartCmsApplication implements CommandLineRunner {
    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(StartCmsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LogFactory.getLog(getClass()).info(environment.getProperty("mangel.propiedad"));
    }
}
