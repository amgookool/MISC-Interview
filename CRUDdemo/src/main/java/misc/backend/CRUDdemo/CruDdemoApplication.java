package misc.backend.CRUDdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// The main class for the application

// This class is annotated with @SpringBootApplication, which is a convenience annotation that adds all of the following:
// - @Configuration: Tags the class as a source of bean definitions for the application context.
// - @EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings
// - This flags the application as a web application and activates key behaviors such as setting up a DispatcherServlet.
// - @ComponentScan: Tells Spring to look for other components, configurations, and services in the hello package, allowing it to find the controllers.

// the main() method uses Spring Boot’s SpringApplication.run() method to launch an application.
// The run() method returns an ApplicationContext where all the beans that were created either by your app or automatically added thanks to Spring Boot are.

@SpringBootApplication
public class CruDdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruDdemoApplication.class, args);
	}

}
