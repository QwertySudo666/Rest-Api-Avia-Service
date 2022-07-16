package com.example.aviaticketsservice.service

import com.example.aviaticketsservice.model.User
import com.example.aviaticketsservice.repository.UserRepository
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.util.UriComponentsBuilder

@Service
class UserService(val userRepository: UserRepository) {
    fun findUsers(): ResponseEntity<List<User>> {
        val users = userRepository.findAll().toList()
        if (users.isEmpty()) {
            return ResponseEntity<List<User>>(HttpStatus.NO_CONTENT)
        }
        return ResponseEntity<List<User>>(users, HttpStatus.OK)
    }

    fun findUser(id: Int): ResponseEntity<User> {
        val user: User
        try {
            user = userRepository.findById(id).get()
        } catch (e: Exception) {
            return ResponseEntity<User>(HttpStatus.NOT_FOUND)
        }
        return ResponseEntity<User>(user, HttpStatus.OK)
    }

//    fun findUser(id: Int): User{
//        return userRepository.findById(id).get()
//    }

    fun saveUser(user: User, uri: UriComponentsBuilder): ResponseEntity<User> {
        return try {
            userRepository.findById(user.id).get()
            //throw Exception("User with id $user.id is existing now")
            ResponseEntity<User>(HttpStatus.BAD_REQUEST)
        } catch (e: Exception) {
            userRepository.save(user)
            val headers = HttpHeaders()
            headers.location = uri.path("/user/get/{id}").buildAndExpand(user.id).toUri()
            ResponseEntity(headers, HttpStatus.CREATED)
        }

    }

    fun updateUser(id: Int, user: User): ResponseEntity<User> {
        return try {
            userRepository.findById(id).get()
            //            .map { u ->
            //                u.firstName = user.firstName
            //                u.lastName = user.lastName
            //                u.password = user.password
            //                u.mail = user.mail
            //                u.tickets = user.tickets
            //               // userRepository.save(u)
            //            }.get()
            userRepository.save(user)
            ResponseEntity<User>(HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity<User>(HttpStatus.NOT_FOUND)
        }
    }

    fun deleteUser(id: Int): ResponseEntity<User> {
        return try {
            userRepository.delete(userRepository.findById(id).get())
            ResponseEntity<User>(HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity<User>(HttpStatus.NOT_FOUND)
        }
    }

    fun deleteUsers() {
        userRepository.deleteAll()
    }
}