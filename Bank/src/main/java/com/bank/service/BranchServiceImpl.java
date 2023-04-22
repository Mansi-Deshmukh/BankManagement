package com.bank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.dto.BranchDTO;
import com.bank.dto.UserDTO;
import com.bank.entities.Bank;
import com.bank.entities.Branch;
import com.bank.entities.Employee;
import com.bank.entities.User;
import com.bank.exception.BankException;
import com.bank.exception.BranchException;
import com.bank.exception.EmployeeException;
import com.bank.exception.UserNotFoundException;
import com.bank.repository.BankRepo;
import com.bank.repository.BranchRepo;

@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchRepo branchRepo;

    @Autowired
    private BankRepo bankRepo;
    
   

    @Override
    public BranchDTO addBranch(Integer bankId, BranchDTO branchDto) throws BranchException, BankException {
        Bank bank = bankRepo.findById(bankId).orElseThrow(() -> new BankException("Bank not found with branch id : "+ bankId));

        Branch branch = branchRepo.findBybranchName(branchDto.getBranchName());
        if(branch != null){
         throw new BranchException("Branch already registered .."+ branch.getBranchName());
        }
       
        Branch addBranch = dtoToBank(branchDto);
        addBranch.setBank(bank);
        bank.getBranches().add(addBranch);
        addBranch.setBranchName(addBranch.getBranchName()+" "+bank.getBankName());
        branchRepo.save(addBranch);
        bankRepo.save(bank);
        return branchToDTO(addBranch);
    }

    // @Override
    // public List<Branch> getAllBranches( ) {
    //     return branchRepo.findAll().stream().collect(Collectors.toList());
    // }

    @Override
    public Branch getBranchById(Integer branchId) throws BranchException {
        Branch branch = branchRepo.findById(branchId).orElseThrow(() -> new BranchException("Branch not found with branch id : "+ branchId));
        return branch;
    }

   

    @Override
    public String deleteBranchById(Integer branchId) throws BranchException {
              Branch branch = branchRepo.findById(branchId).orElseThrow(() -> new BranchException("Branch with "+ branchId+" not found ."));
              branchRepo.delete(branch);
        return " Branch Deleted ..";
    }

    @Override
    public List<User> getUsersByBranchId(Integer branchId) throws BranchException, UserNotFoundException {
       Branch branch = branchRepo.findById(branchId).orElseThrow(() -> new BranchException("Branch with "+ branchId+" not found ."));
       List<User> users = branch.getUsers();
       if(users.isEmpty()) throw new UserNotFoundException("Users not registered yet ..");
       return users;

    }

    @Override
    public List<Employee> getEmployeesByBranchId(Integer branchId) throws BranchException, EmployeeException {
        Branch branch = branchRepo.findById(branchId).orElseThrow(() -> new BranchException("Branch with "+ branchId+" not found ."));
        List<Employee> employees = branch.getEmployees();
        if(employees.isEmpty()) throw new EmployeeException("Employees not registered yet ..");
        return employees;
 
    }
    

    public Branch dtoToBank(BranchDTO bDto){
           Branch branch = new Branch();
           branch.setAddress(bDto.getAddress());
           branch.setBank(bDto.getBank());
           branch.setBranchEmail(bDto.getBranchEmail());
           branch.setBranchId(bDto.getBranchId());
           branch.setBranchName(bDto.getBranchName());
        //    branch.setEmployees(bDto.getEmployees());
           branch.setIfscCode(bDto.getIfscCode());
           branch.setPhone(bDto.getPhone());
        //    branch.setPendingUserAccounts(bDto.getPendingUserAccounts());
        //    branch.setUsers(bDto.getUsers());

           return branch;
    }

    public BranchDTO branchToDTO(Branch branch){
        BranchDTO bDto = new BranchDTO();
        bDto.setAddress(branch.getAddress());
        bDto.setBank(branch.getBank());
        bDto.setBranchEmail(branch.getBranchEmail());
        bDto.setBranchId(branch.getBranchId());
        bDto.setBranchName(branch.getBranchName());
        // bDto.setEmployees(branch.getEmployees());
        bDto.setIfscCode(branch.getIfscCode());
        bDto.setPhone(branch.getPhone());
        // bDto.setPendingUserAccounts(branch.getPendingUserAccounts());
        // bDto.setUsers(branch.getUsers());

        return bDto;
 }

    // @Override
    // public List<Branch> getAllBranchesByBank(Integer bankId) throws BankException, BranchException {


    //    Bank bank = bankRepo.findById(bankId).orElseThrow(() -> new BankException("Bank not found .."));
    //     List<Branch> branchList = new ArrayList<>(); 
    //     for(Branch l : bank.getBranches()){
    //         branchList.add(l);
    //     }

    //     return branchList;







    //    if(bank.getBranches().isEmpty()){
    //     throw new BranchException("Branch not found ..");
    //    }

    //    return bank.getBranches();
    //    List<Branch> list = bank.getBranches();
    //    for(Branch l : list){
    //     System.out.println(l.getBranchName());
    //    }
    //    return list;
    // }

    @Override
    public Integer countBranches(Integer bankId) throws BankException {
        Bank bank = bankRepo.findById(bankId).orElseThrow(() -> new BankException("Bank not found .."));
       List<Branch> list = bank.getBranches();
       Integer i =0;
       for(Branch l : list){
        i++;
        // System.out.println(l.getBranchName());
       }
       return i;
    }

    @Override
    public List<String> getBrachNameByBank(Integer bankId) throws BankException {
        Bank bank = bankRepo.findById(bankId).orElseThrow(() -> new BankException("Bank not found .."));
        List<Branch> branch = bank.getBranches();
        List<String> names = new ArrayList<>();
        for(Branch b : branch){
            names.add(b.getBranchName());
        }
        return names;

    }

    @Override
    public BranchDTO updateBranch(BranchDTO branchDto, Integer branchId) throws BranchException, BankException {
        Branch branch = dtoToBank(branchDto);

        Branch existingBranch = branchRepo.findById(branchId).orElseThrow(() -> new BranchException("Branch not found with id: " + branch.getBranchId()));
    
    // Bank existingBank = existingBranch.getBank();
    // if (!existingBank.getBankId().equals(branch.getBank().getBankId())) {
    //     throw new BankException("Bank not found with id: " + branch.getBank().getBankId());
    // }
    if(branch.getBranchName() != null)
        existingBranch.setBranchName(branch.getBranchName());
    if(branch.getAddress() != null)
       existingBranch.setAddress(branch.getAddress());
    if(branch.getPhone() != null)
       existingBranch.setPhone(branch.getPhone());
    if(branch.getBranchEmail() != null)
       existingBranch.setBranchEmail(branch.getBranchEmail());

       branchRepo.save(existingBranch);

       return branchToDTO(existingBranch);

    }

    // @Override
    // public List<UserDTO> getUserDTOByBranchId(Integer branchId) throws BranchException, UserNotFoundException {
    //    Branch branch = branchRepo.findById(branchId).orElseThrow(()-> new BranchException("Branch not found.."));
    //    if(branch.getUsers().size() < 1){
    //     throw new UserNotFoundException("No user registered ..");
    //    }
    //    List<User> users = branch.getUsers();
    //    List<UserDTO> list = new ArrayList<>();
    //    for(User u : users){
    //     //    UserDTO dto = userToDt
    //    }
    //    return list;
    // }

    @Override
    public List<BranchDTO> getBranchesByBank(Integer bankId) throws BankException, BranchException {
        Bank bank = bankRepo.findById(bankId).orElseThrow(() -> new BankException("Bank not found"));
        List<Branch> branchList = bank.getBranches();
        if(branchList.size() < 1){
            throw new BranchException("No branch found ");
        }
        List<BranchDTO> list = new ArrayList<>();
        for(Branch b : branchList){
            BranchDTO dto = branchToDTO(b);
            list.add(dto);
        }
       
        return list;
    }
}
