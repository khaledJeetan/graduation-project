package com.project.nextstep.services;

import com.project.nextstep.entity.Budget;
import com.project.nextstep.repositories.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final TaskService taskService;

    @Autowired
    public BudgetService(BudgetRepository budgetRepository, TaskService taskService) {
        this.budgetRepository = budgetRepository;
        this.taskService = taskService;
    }

    public Budget getTaskBudget(Long taskId) {
        Budget budget = taskService.getTask(taskId).getBudget();
        if (budget == null)
            throw new IllegalStateException("Task Has No Budget to update!! Create New One");
        return budget;
    }

    public Budget createTaskBudget(Long taskId,Budget budget) {
        return budgetRepository.save(budget);
    }

    @Transactional
    public Budget updateTaskBudget(Long taskId,Budget budget) {
        Budget oldBudget = getTaskBudget(taskId);
        oldBudget.setActualAmount(budget.getActualAmount());
        oldBudget.setEstimatedAmount(budget.getEstimatedAmount());
        oldBudget.setCategories(budget.getCategories());
        oldBudget.setNote(budget.getNote());
        return oldBudget;
    }

    @Transactional
    public Budget deleteTaskBudget(Long taskId) {
        Budget deletedBudget = getTaskBudget(taskId);
        budgetRepository.deleteById(deletedBudget.getId());
        taskService.getTask(taskId).setBudget(null);
        return deletedBudget;
    }
}
