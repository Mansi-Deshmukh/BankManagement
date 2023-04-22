package com.bank.service;

import java.util.List;

import com.bank.dto.EmployeeDTO;
import com.bank.entities.Employee;
import com.bank.exception.BankException;
import com.bank.exception.BranchException;
import com.bank.exception.EmployeeException;
import com.bank.exception.PasswordException;
import com.bank.exception.UserNotFoundException;

public interface EmployeeService {
    public EmployeeDTO addEmployee(EmployeeDTO empDTO, Integer branchId) throws BranchException, EmployeeException;

    public EmployeeDTO updateEmployee(EmployeeDTO userDTO, Integer empId, String password) throws EmployeeException, PasswordException;

    public List<Employee> getAllEmployee();

    public List<EmployeeDTO> getEmployeeByBank(Integer bankId) throws BankException;

    public List<EmployeeDTO> getEmployeeByBranch(Integer branchId) throws BranchException;
    
    public EmployeeDTO getEmployeeById(Integer userId) throws EmployeeException;
    public String deleteEmployeeById(Integer userId) throws EmployeeException;
}
