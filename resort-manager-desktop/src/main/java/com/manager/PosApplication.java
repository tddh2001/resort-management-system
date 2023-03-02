package com.manager;

import com.manager.service.BaseRepositoryImpl;
import com.manager.views.Login;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
public class PosApplication extends Application {

    private static ConfigurableApplicationContext configurableApplicationContext;

    @Override
    public void init() throws Exception {
        this.configurableApplicationContext = SpringApplication.run(PosApplication.class);
    }

    @Override
    public void stop() throws Exception {
        configurableApplicationContext.close();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Login.loadView(stage);
    }

    public static ConfigurableApplicationContext getConfigurableApplicationContext() {
        return configurableApplicationContext;
    }
}

