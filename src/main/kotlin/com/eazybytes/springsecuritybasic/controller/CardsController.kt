package com.eazybytes.springsecuritybasic.controller

import com.eazybytes.springsecuritybasic.model.Cards
import com.eazybytes.springsecuritybasic.model.Customer
import com.eazybytes.springsecuritybasic.repository.CardsRepository
import com.eazybytes.springsecuritybasic.repository.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class CardsController(
    @Autowired
    private val cardsRepository: CardsRepository?,
    @Autowired
    private val customerRepository: CustomerRepository?
) {

    @GetMapping("/myCards")
    fun getCardDetails(@RequestParam email: String): List<Cards?>? {
        val customers: List<Customer> = customerRepository!!.findByEmail(email)
        if (customers.isNotEmpty()) {
            val cards = cardsRepository!!.findByCustomerId(customerId = customers[0].id)
            return cards
        }

        return null
    }
}