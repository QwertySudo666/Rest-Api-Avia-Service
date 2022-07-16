package com.example.aviaticketsservice.controller

import com.example.aviaticketsservice.service.TicketService
import com.example.aviaticketsservice.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("ticket")
class TicketRestController(val ticketService: TicketService) {
    @GetMapping("get-by-userId/{userId}")
    fun getUserByUserId(@PathVariable userId: Int) = ticketService.findTicketsByUserId(userId)
}