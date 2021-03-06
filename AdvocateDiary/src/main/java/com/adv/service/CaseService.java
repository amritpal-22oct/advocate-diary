package com.adv.service;

import java.util.List;

import com.adv.entities.Case;

public interface CaseService {

	public void addCase(Case p);

	public void updateCase(Case p);

	public List<Case> listCases();

	public Case getCaseById(int id);

	public void removeCase(int id);

}
