package com.example.aviaticketsservice.service

import com.example.aviaticketsservice.model.Ticket
import com.example.aviaticketsservice.repository.TicketRepository
import com.example.aviaticketsservice.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class TicketService(val ticketRepository: TicketRepository, val userRepository: UserRepository) {
    fun findAll(): MutableIterable<Ticket> = ticketRepository.findAll()

    //Get all tickets by user ID
    fun findTicketsByUserId(userId: Int): ResponseEntity<List<Ticket>> {
        return try {
            //invoke to get exception
            userRepository.findById(userId).get()
            val tickets = ticketRepository.findTicketsByUserId(userId)
            ResponseEntity<List<Ticket>>(tickets, HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity<List<Ticket>>(HttpStatus.NOT_FOUND)
        }
    }

    //Get one ticket by user ID and ticket ID
    fun findTicketByUserIdAndTicketId(userId: Int, ticketId: Int): ResponseEntity<Ticket> {
        return try {
            //ф-я get() use for отримання помилки, помилка означає,
            // що користувача з заданим ID немає в БД
            userRepository.findById(userId).get()
            val ticket = ticketRepository.findTicketByUserIdAndId(userId, ticketId)
            ResponseEntity<Ticket>(ticket, HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity<Ticket>(HttpStatus.NOT_FOUND)
        }
    }

    fun saveTicketByUserId(userId: Int, ticket: Ticket): ResponseEntity<Ticket> {
        return try {
            val user = userRepository.findById(userId).get()
            val savedTicket = ticketRepository.save(ticket)
            user.tickets.add(savedTicket)
            userRepository.save(user)
            ResponseEntity<Ticket>(HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity<Ticket>(HttpStatus.BAD_REQUEST)
        }
    }
}