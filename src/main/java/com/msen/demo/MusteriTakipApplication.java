package com.msen.demo;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.msen.demo.model.Customer;
import com.msen.demo.repository.CustomerRepository;


@SpringBootApplication
public class MusteriTakipApplication  implements CommandLineRunner {
	
	private final CustomerRepository customerRepository;

	public MusteriTakipApplication(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}


	public static void main(String[] args) {
		SpringApplication.run(MusteriTakipApplication.class, args);
	}
	
	
	@Override
	public void run(String... args) throws Exception {
		Customer book1 = Customer.builder()
				.accountActivity(null)
				.balance(20.0)
				.createDate(Instant.now())
				.lastName("Senyurt")
				.name("Mustafa")
				.phoneNumber("5555")
				.build();
		
		Customer book2 = Customer.builder()
				.accountActivity(null)
				.balance(20.0)
				.createDate(Instant.now())
				.lastName("Senyurt")
				.name("Mustafa11")
				.phoneNumber("5555")
				.build();
		
		Customer book3 = Customer.builder()
				.accountActivity(null)
				.balance(20.0)
				.createDate(Instant.now())
				.lastName("Senyurt")
				.name("Mustafa22")
				.phoneNumber("5555")
				.build();
		
		customerRepository.saveAll(Arrays.asList(book1,book2,book3));
		
	}

}
