package com.eazybytes.springsecuritybasic.controller

import com.eazybytes.springsecuritybasic.model.Accounts
import com.eazybytes.springsecuritybasic.model.Customer
import com.eazybytes.springsecuritybasic.repository.AccountsRepository
import com.eazybytes.springsecuritybasic.repository.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class AccountController(
    @Autowired
    private val accountsRepository: AccountsRepository,
    @Autowired
    private val customerRepository: CustomerRepository
) {


    @GetMapping("/myAccount")
    fun getAccountDetails(@RequestParam email: String): Accounts? {
        /*val accounts = accountsRepository.findByCustomerId(id)
        return accounts*/
        val customers: List<Customer> = customerRepository.findByEmail(email)
        if (customers.isNotEmpty()) {
            val accounts: Accounts? = accountsRepository.findByCustomerId(customers[0].id)
            return accounts
        }

        return null
    }
}