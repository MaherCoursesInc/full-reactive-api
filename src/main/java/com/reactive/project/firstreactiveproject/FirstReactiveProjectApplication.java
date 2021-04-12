package com.reactive.project.firstreactiveproject;

import com.reactive.project.firstreactiveproject.model.Car;
import com.reactive.project.firstreactiveproject.model.User;
import com.reactive.project.firstreactiveproject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@SpringBootApplication
@Slf4j
public class FirstReactiveProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstReactiveProjectApplication.class, args);
	}

	@Bean
	ApplicationRunner init(UserRepository userRepository) {

		List<Car> jamesCars =
				Arrays.asList(
						Car.builder()
								.brand("Audi")
								.colour("Black")
								.build(),
						Car.builder()
								.brand("Chevrolet")
								.colour("White")
								.build()
				);
		User james = User
				.builder()
				.id(UUID.randomUUID())
				.name("James")
				.email("james@localhost.com")
				.cars(jamesCars)
				.build();

		List<Car> jackCars =
				Arrays.asList(
						Car.builder()
								.brand("Kia")
								.colour("White")
								.build(),
						Car.builder()
								.brand("Mazda")
								.colour("Silver")
								.build()
				);
		User jack = User
				.builder()
				.id(UUID.randomUUID())
				.name("Jack")
				.email("jack@localhost.com")
				.cars(jackCars)
				.build();

		Set<User> users = Set.of(james, jack);

		return args -> {
			userRepository
					.deleteAll()
					.thenMany(
							Flux.just(users)
									.flatMap(userRepository::saveAll)
					)
					.thenMany(userRepository.findAll())
					.subscribe(user -> log.info("saving " + user.toString()));
		};
	}

}
