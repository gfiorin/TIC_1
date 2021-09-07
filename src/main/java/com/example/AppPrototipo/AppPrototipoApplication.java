package com.example.AppPrototipo;

import com.example.AppPrototipo.ui.JavaFXApplication;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AppPrototipoApplication {

	private static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		AppPrototipoApplication.context = SpringApplication.run(AppPrototipoApplication.class);
		Application.launch(JavaFXApplication.class, args);
	}

	public static ConfigurableApplicationContext getContext() {
		return context;
	}
}
