package com.example.aviaticketsservice.service

import com.example.aviaticketsservice.model.Ticket
import com.example.aviaticketsservice.repository.TicketRepository
import com.example.aviaticketsservice.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class TicketService(val ticketRepository: TicketRepository, val userRepository: UserRepository) {
    fun findTickets(): MutableIterable<Ticket> = ticketRepository.findAll()

    fun findTicketsByUserId(userId: Int): ResponseEntity<List<Ticket>> {
        return try {
            val user = userRepository.findById(userId).get() //invoke to get exeption
            val tickets = ticketRepository.findTicketsByUserId(userId)
            ResponseEntity<List<Ticket>>(tickets, HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity<List<Ticket>>(HttpStatus.NOT_FOUND)
        }
    }

}