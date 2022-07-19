package com.example.aviaticketsservice.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
@Table(name = "tickets")
data class Ticket(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User,
    val fromCity: String,
    val toCity: String,
    val planeNumber: String,
    val place: Int,
    val date: String,
    val time: String
)