package br.com.bnck.rabbitmq.producer;

import br.com.bnck.rabbitmq.producer.entity.Employee;
import br.com.bnck.rabbitmq.producer.producer.EmployeeJsonProducer;
import br.com.bnck.rabbitmq.producer.producer.HelloRabbitProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDate;

@SpringBootApplication
//@EnableScheduling
@Slf4j
public class Application implements CommandLineRunner {

	@Autowired
	private EmployeeJsonProducer producer;


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		for (int i = 0; i < 5; i++) {
			var employee = new Employee("emp " + i, "Employee " + i, LocalDate.now());
			producer.sendMessage(employee);
			log.info("Employee sento to queue: {}", employee.toString());
		}
	}
}
