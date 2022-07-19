package com.example.aviaticketsservice.controller

import com.example.aviaticketsservice.service.TicketService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import com.example.aviaticketsservice.model.Ticket

@RestController
@RequestMapping("ticket")
class TicketRestController(val ticketService: TicketService) {
    @GetMapping("get-all")
    fun getAll(): MutableIterable<Ticket> = ticketService.findAll()

    //get all tickets by user id
    @GetMapping("get-all-by-userId/{userId}")
    fun getTicketsByUserId(@PathVariable userId: Int): ResponseEntity<List<Ticket>> =
        ticketService.findTicketsByUserId(userId)

    //get ticket by user id and ticket id
    @GetMapping("get-by-uId-and-tId/{userId}/{ticketId}")
    fun getTicketByUserIdAndTicketId(@PathVariable userId: Int, @PathVariable ticketId: Int): ResponseEntity<Ticket> =
        ticketService.findTicketByUserIdAndTicketId(userId, ticketId)

    //add new ticket to user
    @PostMapping("save/{userId}")
    fun addNewTicket(@PathVariable userId: Int, @RequestBody ticket: Ticket): ResponseEntity<Ticket> =
        ticketService.saveTicketByUserId(userId, ticket)
}