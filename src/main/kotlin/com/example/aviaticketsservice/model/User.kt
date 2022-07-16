package com.example.aviaticketsservice.model

import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int,
    var mail: String,
    var firstName: String,
    var lastName: String,
    var password: String,
    @OneToMany(mappedBy = "user")
    var tickets: List<Ticket> = emptyList()
)