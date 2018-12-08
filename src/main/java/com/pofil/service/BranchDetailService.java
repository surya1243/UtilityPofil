package com.pofil.service;

import java.util.List;
import java.util.Optional;

import com.pofil.model.Branch;

public interface BranchDetailService {
	
	Optional<Branch> getBranchById(String id);

	Branch getBranchByName(String branchName);

	List<Branch> getAllBranch();

	Branch saveBranch(Branch branch);

}
