package com.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.entities.Branch;

@Repository
public interface BranchRepo extends JpaRepository<Branch, Integer>{
 
    public Branch findBybranchName(String branchName);
    public List<Branch> findAll();
}
