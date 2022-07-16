package br.com.bnck.rabbitmq.producer;

import br.com.bnck.rabbitmq.producer.entity.Picture;
import br.com.bnck.rabbitmq.producer.producer.RetryPictureProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
@Slf4j
public class Application implements CommandLineRunner {

	@Autowired
	private RetryPictureProducer producer;

	// valid sources
	private final List<String> SOURCES = List.of("mobile", "web");

	// valid types
	private final List<String> TYPES = List.of("jpg", "png", "svg");

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		retryPictureMethod();
	}

	private void retryPictureMethod() throws IOException {
		for (int i = 0; i < 1; i++) {
			var picture = new Picture();
			picture.setName("Picture " + i);
			picture.setSize(ThreadLocalRandom.current().nextLong(9000, 10000));
			picture.setSource(SOURCES.get(i % SOURCES.size()));
			picture.setType(TYPES.get(i % TYPES.size()));
			producer.sendMessage(picture);
			log.info("Picture: {}, send to queue", picture);
		}
	}
}
