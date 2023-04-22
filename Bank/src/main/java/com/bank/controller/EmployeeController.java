package com.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.dto.EmployeeDTO;
import com.bank.entities.Employee;
import com.bank.exception.BankException;
import com.bank.exception.BranchException;
import com.bank.exception.EmployeeException;
import com.bank.exception.PasswordException;
import com.bank.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add/{branchId}")
    public ResponseEntity<EmployeeDTO> addEmployeeHandler(@RequestBody EmployeeDTO empDto, @PathVariable("branchId") Integer branchId) throws BranchException, EmployeeException{
        EmployeeDTO emp = employeeService.addEmployee(empDto, branchId);
         return new ResponseEntity<EmployeeDTO>(emp, HttpStatus.CREATED);
     }

     @PutMapping("/update/{empId}/{password}")
     public ResponseEntity<EmployeeDTO> updateEmployeeHandler(@RequestBody EmployeeDTO empDto,@PathVariable("empId") Integer empId,@PathVariable("password") String password) throws EmployeeException , PasswordException{
         EmployeeDTO emp = employeeService.updateEmployee(empDto, empId, password);
         return new ResponseEntity<EmployeeDTO>(emp, HttpStatus.OK);
     }

     @GetMapping("/all_employee")
     public ResponseEntity<List<Employee>> getAllEmployeeHandler(){
         List<Employee> eList = employeeService.getAllEmployee();
         return new ResponseEntity<List<Employee>>(eList, HttpStatus.OK);

     }

    //  getEmployeeByBank
    @GetMapping("/bank_employee/{bankId}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeeByBankHandler(@PathVariable("bankId") Integer bankId) throws BankException{
        List<EmployeeDTO> eList = employeeService.getEmployeeByBank(bankId);
        return new ResponseEntity<List<EmployeeDTO>>(eList, HttpStatus.OK);

    }

    @GetMapping("/branch_employee/{branchId}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeeByBranchHandler(@PathVariable("branchId") Integer branchId) throws BranchException{
        List<EmployeeDTO> eList = employeeService.getEmployeeByBranch(branchId);
        return new ResponseEntity<List<EmployeeDTO>>(eList, HttpStatus.OK);

    }

    @GetMapping("/id/{empId}")
    public ResponseEntity<EmployeeDTO> getEmployeeByIdHandler(@PathVariable("empId") Integer empId) throws EmployeeException{
        EmployeeDTO emp = employeeService.getEmployeeById(empId);
        return new ResponseEntity<EmployeeDTO>(emp, HttpStatus.OK);

    }
    
    @DeleteMapping("/delete/{empId}")
    public ResponseEntity<String> deleteUserHandler(@PathVariable("empId") Integer empId) throws EmployeeException {
       String message = employeeService.deleteEmployeeById(empId);
       return new ResponseEntity<String>(message, HttpStatus.OK);
   }
}
