package demo;

import reactor.core.publisher.Mono;
import retrofit2.http.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.square.retrofit.core.RetrofitClient;
import org.springframework.cloud.square.retrofit.webclient.EnableRetrofitClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Olga Maciaszek-Sharma
 */
@SpringBootApplication
@RestController
@EnableRetrofitClients
public class HelloClientApplication {

	@Autowired
	HelloClient client;

	public static void main(String[] args) {
		SpringApplication.run(HelloClientApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public WebClient.Builder builder() {
		return WebClient.builder();
	}

	@GetMapping("/")
	public Mono<String> hello() {
		return client.hello();
	}

	@RetrofitClient("HelloServer")
	interface HelloClient {
		@GET("/")
		Mono<String> hello();
	}
}
