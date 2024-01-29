package com.eazybytes.springsecuritybasic.controller

import com.eazybytes.springsecuritybasic.model.AccountTransactions
import com.eazybytes.springsecuritybasic.model.Customer
import com.eazybytes.springsecuritybasic.repository.AccountTransactionsRepository
import com.eazybytes.springsecuritybasic.repository.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class BalanceController(
    @Autowired
    private val accountTransactionsRepository: AccountTransactionsRepository?,
    @Autowired
    private val customerRepository: CustomerRepository?
) {


    @GetMapping("/myBalance")
    fun getBalanceDetails(@RequestParam email: String): List<AccountTransactions?>? {
        val customers: List<Customer> = customerRepository!!.findByEmail(email)
        if (customers.isNotEmpty()) {
            val accountTransactions = accountTransactionsRepository!!.findByCustomerIdOrderByTransactionDtDesc(customers[0].id)
            return accountTransactions
        }
        return null
    }
}