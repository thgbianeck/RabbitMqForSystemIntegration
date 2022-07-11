package br.com.bnck.rabbitmq.producer;

import br.com.bnck.rabbitmq.producer.entity.Furniture;
import br.com.bnck.rabbitmq.producer.entity.Picture;
import br.com.bnck.rabbitmq.producer.producer.FurnitureProducer;
import br.com.bnck.rabbitmq.producer.producer.PictureProducerTwo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
@Slf4j
public class Application implements CommandLineRunner {

	@Autowired
	private FurnitureProducer producer;

	// valid sources
	private final List<String> COLORS = List.of("white", "red", "green");

	// valid types
	private final List<String> MATERIALS = List.of("wood", "plastic", "steel");

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		for (int i = 0; i < 10; i++) {
			var furniture = new Furniture();
			furniture.setName("Furniture " + i);
			furniture.setColor(COLORS.get(i % COLORS.size()));
			furniture.setMaterial(MATERIALS.get(i % MATERIALS.size()));
			furniture.setPrice(i);
			producer.sendMessage(furniture);
			log.info("Furniture {}, send to queue", furniture);
		}

	}
}
