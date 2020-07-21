package com.loizenai.springboot.pagingansorting;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.loizenai.springboot.pagingansorting.model.Customer;
import com.loizenai.springboot.pagingansorting.repository.CustomerRepository;

@SpringBootApplication
public class SpringBootPagingAndSortingApplication implements CommandLineRunner{

	@Autowired
	CustomerRepository customerRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootPagingAndSortingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Customer> customers = Arrays.asList(	new Customer("Jack", "Smith", "Massachusetts", 23, 4000)
						, new Customer("Adam", "Johnson", "New York", 27, 3000)
						, new Customer("Katherin", "Carter", "Washington DC", 26, 4000)
						, new Customer("Jack", "London", "Nevada", 33, 4000)
						, new Customer("Jason", "Bourne", "California", 36, 3000)
						, new Customer("Blade", "Johnson", "Ohio", 18, 3000)
						, new Customer("Carol", "Carter", "Florida", 23, 4000)
						, new Customer("Avery", "Alvin", "Washington DC", 41, 3000)
						, new Customer("Dana", "Bay", "Texas", 32, 4000)
						, new Customer("Tom", "Bourne", "Colorado", 28, 3500)
						, new Customer("Ardys", "Bean", "Alaska", 23, 4000)
						, new Customer("Carol", "Carter", "Arizona", 26, 3500)
						, new Customer("Avery", "Zane", "Virginia", 31, 4000)
						, new Customer("Barric", "Weldin", "Oregon", 52, 3000)
						, new Customer("Galen", "Wayt", "Missouri", 48, 4000)
						, new Customer("Mayer", "Acomb", "Wisconsin", 32, 4000)
						, new Customer("Sadie", "Albright", "Tennessee", 23, 3500)
						, new Customer("Norvell", "Baily", "Oregon", 41, 4000)
						, new Customer("Tanzi", "Baney", "North Dakota", 26, 4000)
						, new Customer("Osric", "Callender", "New Mexico", 29, 3000)
						, new Customer("Trudy", "Zane", "Vermont", 31, 3500)
						, new Customer("Reynold", "Zone", "Wyoming", 43, 3000)
						, new Customer("Udele", "Wheetley", "Michigan", 37, 3500)
						, new Customer("Blackburn", "Atkinson", "Illinois", 19, 4000)
						, new Customer("Cotovatre", "Bailey", "Delaware", 26, 4000));
		
		customerRepository.saveAll(customers);
	}
}
