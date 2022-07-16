package com.example.aviaticketsservice.repository

import com.example.aviaticketsservice.model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Int>