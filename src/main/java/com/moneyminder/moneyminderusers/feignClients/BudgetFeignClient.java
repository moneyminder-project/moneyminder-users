package com.moneyminder.moneyminderusers.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "budgetService", url = "localhost:18080/api/expenses/budget", configuration = FeignConfig.class)
public interface BudgetFeignClient {

    @GetMapping("/budget-name/{id}")
    String getBudgetNameById(@PathVariable String id);

}
