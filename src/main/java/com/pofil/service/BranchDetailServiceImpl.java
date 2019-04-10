package com.pofil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pofil.model.Branch;
import com.pofil.repository.BranchRepository;
import com.pofil.repository.UserRepository;
@Service
public class BranchDetailServiceImpl implements BranchDetailService {
	
	private BranchRepository branchRepository;
	
	@Autowired
	public BranchDetailServiceImpl(BranchRepository branchRepository) {
		this.branchRepository = branchRepository;
	}

	@Override
	public Optional<Branch> getBranchById(String id) {
		return branchRepository.findById(id);
	}

	@Override
	public Branch getBranchByName(String branchName) {
		return branchRepository.findByBranchName(branchName);
	}

	@Override
	public List<Branch> getAllBranch() {
		return branchRepository.findAll(Sort.by(Sort.Direction.ASC, "branchName"));
	}

	@Override
	public Branch saveBranch(Branch branch) {
		branch.setEnabled(true);
		branchRepository.save(branch);
		return branchRepository.save(branch);
	}

}
