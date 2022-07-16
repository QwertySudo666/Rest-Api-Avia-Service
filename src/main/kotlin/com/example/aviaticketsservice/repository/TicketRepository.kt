package com.example.aviaticketsservice.repository

import com.example.aviaticketsservice.model.Ticket
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TicketRepository : CrudRepository<Ticket, Int>
{
    fun findTicketsByUserId(userId: Int): List<Ticket>
}