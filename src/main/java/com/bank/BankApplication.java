package com.bank;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bank.dto.BankDTO;
import com.bank.entities.Bank;
import com.bank.entities.Branch;
import com.bank.service.BankService;
import com.bank.service.BranchService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class BankApplication implements CommandLineRunner {

	@Autowired
	private BranchService branchService;

	@Autowired
	private BankService bankService;

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	 	// BankDTO bank = new BankDTO();
        // bank.setBankName("ICICI");

		// List<Bank> bList = bankService.getAllBanks();
	 
		// // List<Branch> list = branchService.getAllBranchesByBank(1);
        // for(Bank b : bList){
		// 	System.out.println(b.getBankName());
		// }
		// System.out.println(bList);
	}

	

}
