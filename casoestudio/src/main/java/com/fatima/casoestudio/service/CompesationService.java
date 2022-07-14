package com.fatima.casoestudio.service;


import java.time.LocalDate;
import java.util.List;

import com.fatima.casoestudio.entity.Compesation;

import com.fatima.casoestudio.entity.CompesationFindForm;
import com.fatima.casoestudio.entity.MonthsAmount;


public interface CompesationService {

	public Iterable<MonthsAmount> getCompesationsByMonth(Long employeeId, CompesationFindForm cff);
	
	public Iterable<Compesation> getCompesationsByIdAndDate(Long employeeId, CompesationFindForm cff);
	
	public Compesation createCompesation(Compesation compesation) throws Exception;
	
	public Compesation getCompesationById(Long id) throws Exception;
	
	public Compesation updateCompesation(Compesation compesation) throws Exception;
	
	//public Long getCompesationByEmployeeId(Long id) throws Exception;

	//public List<Compesation> getCompesationByFilter(LocalDate desde, LocalDate hasta);
}
