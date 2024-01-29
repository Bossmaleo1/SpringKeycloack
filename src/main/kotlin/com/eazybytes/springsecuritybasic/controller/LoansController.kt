package com.eazybytes.springsecuritybasic.controller

import com.eazybytes.springsecuritybasic.model.Customer
import com.eazybytes.springsecuritybasic.model.Loans
import com.eazybytes.springsecuritybasic.repository.CustomerRepository
import com.eazybytes.springsecuritybasic.repository.LoanRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PostAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class LoansController(
    @Autowired
    private val loanRepository: LoanRepository?,
    @Autowired
    private val customerRepository: CustomerRepository?
) {


    @GetMapping("/myLoans")
    @PostAuthorize("hasRole('USER')")
    fun getLoanDetails(@RequestParam email: String): List<Loans?>? {
        val customers: List<Customer> = customerRepository!!.findByEmail(email)
        if (customers.isNotEmpty()) {
            val loans = loanRepository!!.findByCustomerIdOrderByStartDtDesc(customerId = customers[0].id)
            return loans
        }
        return null
    }
}