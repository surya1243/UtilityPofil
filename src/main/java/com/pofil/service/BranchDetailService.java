package com.pofil.service;

import java.util.List;
import java.util.Optional;

import com.pofil.model.Branch;

public interface BranchDetailService {
	
	Optional<Branch> getBranchById(String id);
	Optional<Branch> getBranchByBranchName(String branchName);
	List<Branch> getAllBranch();
	Branch saveBranch(Branch branch);

}
