package com.bank.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.dto.EmployeeDTO;
import com.bank.entities.Bank;
import com.bank.entities.Branch;
import com.bank.entities.Employee;
import com.bank.exception.BankException;
import com.bank.exception.BranchException;
import com.bank.exception.EmployeeException;
import com.bank.exception.PasswordException;
import com.bank.repository.BankRepo;
import com.bank.repository.BranchRepo;
import com.bank.repository.EmployeeRepo;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    

    @Autowired
    private BranchRepo branchRepo;

    @Autowired
    private EmployeeRepo employeeRepo;


    @Autowired
    private BankRepo bankRepo;

    public Employee dtoToEmployee(EmployeeDTO empDTO){

        Employee emp = new Employee();
        // emp.setAccounts(empDTO.getAccounts());
        emp.setBranch(empDTO.getBranch());
        emp.setEmail(empDTO.getEmail());
        emp.setEmployeeId(empDTO.getEmployeeId());
        emp.setFirstName(empDTO.getFirstName());
        emp.setLastName(empDTO.getLastName());
        emp.setPhone(empDTO.getPhone());
        emp.setPassoword(empDTO.getPassoword());
        return emp;
    }

    public EmployeeDTO employeeToDto(Employee empDTO){

        EmployeeDTO emp = new EmployeeDTO();
        // emp.setAccounts(empDTO.getAccounts());
        emp.setBranch(empDTO.getBranch());
        emp.setEmail(empDTO.getEmail());
        emp.setEmployeeId(empDTO.getEmployeeId());
        emp.setFirstName(empDTO.getFirstName());
        emp.setLastName(empDTO.getLastName());
        emp.setPhone(empDTO.getPhone());
        emp.setPassoword(empDTO.getPassoword());
        return emp;
    }

    @Override
    public EmployeeDTO addEmployee(EmployeeDTO empDTO, Integer branchId) throws BranchException , EmployeeException{
        Branch branch = branchRepo.findById(branchId).orElseThrow(() -> new BranchException("Branch with "+ branchId+" not found ."));
        List<Employee> empList = branch.getEmployees();
        Employee emp = dtoToEmployee(empDTO);
        if(empList.size() >0){
             for(Employee e : empList){
                if(e.getEmail().equals(emp.getEmail())){
                    throw new EmployeeException("Employee with email  "+ emp.getEmail() +" already joinned .. ");
                }
             }
        }
        emp.setBranch(branch);
        branch.getEmployees().add(emp);

        employeeRepo.save(emp);
        branchRepo.save(branch);

        return employeeToDto(emp);
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO empDTO, Integer empId, String password) throws EmployeeException , PasswordException{
        Employee emp = employeeRepo.findById(empId).orElseThrow(()-> new EmployeeException("Employee not found Provide valid employee Id.."));
         Employee e = dtoToEmployee(empDTO);
         if(!password.equals(emp.getPassoword())){
            throw new PasswordException("Incorrect Password");
         }
        if(e.getLastName() != null)
           emp.setLastName(e.getLastName());
        if(e.getFirstName() != null)
           emp.setFirstName(e.getFirstName());
        if(e.getPhone() != null)
           emp.setPhone(e.getPhone());

        employeeRepo.save(emp);
        return employeeToDto(emp);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepo.findAll();
    }

    @Override
    public List<EmployeeDTO> getEmployeeByBank(Integer bankId) throws BankException {
        Bank bank = bankRepo.findById(bankId).orElseThrow(()-> new BankException("Bank not found "));
        List<Branch> branchList = bank.getBranches();
        List<EmployeeDTO> list = new ArrayList<>();
        for(Branch b : branchList){
          List<Employee> empList = b.getEmployees();
          for(Employee e : empList){
              EmployeeDTO addEmp = employeeToDto(e);
              list.add(addEmp);
          }
        }

        return list;

    }

    @Override
    public List<EmployeeDTO> getEmployeeByBranch(Integer branchId) throws BranchException {
        Branch branch = branchRepo.findById(branchId).orElseThrow(() -> new BranchException("Branch with "+ branchId+" not found ."));
        List<Employee> listEmp =  branch.getEmployees();
        List<EmployeeDTO> dtoList = new ArrayList<>();
        for(Employee e : listEmp){
            EmployeeDTO addEmp = employeeToDto(e);
            dtoList.add(addEmp);
        }
        return dtoList;
    }

    @Override
    public EmployeeDTO getEmployeeById(Integer empId) throws EmployeeException {
        Employee emp = employeeRepo.findById(empId).orElseThrow(()-> new EmployeeException("Employee not found .."));
        return employeeToDto(emp);
    }

    @Override
    public String deleteEmployeeById(Integer empId) throws EmployeeException {
        Employee emp = employeeRepo.findById(empId).orElseThrow(()-> new EmployeeException("Employee not found .."));
        employeeRepo.delete(emp);
       return "Employee Deleted ..";
    }
}
