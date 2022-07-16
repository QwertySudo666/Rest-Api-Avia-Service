package com.example.aviaticketsservice.model

import javax.persistence.*

@Entity
@Table(name = "tickets")
data class Ticket(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val ticketId: Int,
    @ManyToOne
    @JoinColumn(name = "id")
    val user: User,
    val fromCity: String,
    val toCity: String,
    val planeNumber: String,
    val place: Int,
    val date: String,
    val time: String
)