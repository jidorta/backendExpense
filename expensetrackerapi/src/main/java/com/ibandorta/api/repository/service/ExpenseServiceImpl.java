package com.ibandorta.api.repository.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ibandorta.api.entity.Expense;
import com.ibandorta.api.exceptions.ResourceNotFounException;
import com.ibandorta.api.repository.ExpenseRepository;

@Service
public class ExpenseServiceImpl implements ExpenseService{
	
	@Autowired
	private ExpenseRepository expenseRepo;

	@Override
	public Page<Expense> getAllExpenses(Pageable page) {
		// TODO Auto-generated method stub
		return expenseRepo.findAll(page);
	}

	@Override
	public Expense getExpenseById(Long id) {
		Optional <Expense> expense = expenseRepo.findById(id);
		if (expense.isPresent()) {
			return expense.get();
		}
		throw new ResourceNotFounException("Expense is not found for the id " +id) ;
	}

	@Override
	public void deleteExpenseById(Long id) {
		Expense expense =getExpenseById(id);
		expenseRepo.delete(expense);
		
	}

	@Override
	public Expense saveExpenseDetails(Expense expense) {
		return expenseRepo.save(expense);
	}

	@Override
	public Expense updateExpenseDetails(Long id, Expense expense) {
		Expense expenseexisting = getExpenseById(id);
		expenseexisting.setDescription(expense.getDescription() != null ? expense.getDescription() : expenseexisting.getDescription());
		expenseexisting.setCategory(expense.getCategory() != null ? expense.getCategory() : expenseexisting.getCategory());
		expenseexisting.setDate(expense.getDate() != null ? expense.getDate() : expenseexisting.getDate());
		expenseexisting.setAmount(expense.getAmount() != null ? expense.getAmount() : expenseexisting.getAmount());
		return expenseRepo.save(expenseexisting);
		
	}

	@Override
	public List<Expense> readByCategory(String category, Pageable page) {
		return expenseRepo.findByCategory(category, page).toList();
	}

	@Override
	public List<Expense> readByName(String keyword, Pageable page) {
		return expenseRepo.findByNameContaining(keyword, page).toList();
	}

	@Override
	public List<Expense> readByDate(Date startDate, Date endDate, Pageable page) {
		if (startDate == null) {
			startDate = new Date(0);
		}
		
		if (endDate == null) {
			endDate = new Date(System.currentTimeMillis());
		}
		return expenseRepo.findByDateBetween(startDate, endDate, page).toList();
	}

}
