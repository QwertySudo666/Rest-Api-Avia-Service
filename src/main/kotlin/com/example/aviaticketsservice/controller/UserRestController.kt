package com.example.aviaticketsservice.controller

import com.example.aviaticketsservice.model.User
import com.example.aviaticketsservice.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("user")
class UserRestController(val userService: UserService) {
    @GetMapping("get-all")
    fun getUsers(): ResponseEntity<List<User>> = userService.findUsers()

    @GetMapping("get/{id}")
    fun getUser(@PathVariable id: Int): ResponseEntity<User> = userService.findUser(id)

    @PostMapping("save")
    fun addNewUser(@RequestBody user: User, uri: UriComponentsBuilder): ResponseEntity<User> =
        userService.saveUser(user, uri)

    @PutMapping("update/{id}")
    fun updateUser(@PathVariable id: Int, @RequestBody user: User) = userService.updateUser(id, user)

    @DeleteMapping("delete/{id}")
    fun deleteUser(@PathVariable id: Int) = userService.deleteUser(id)

    @DeleteMapping("delete-all")
    fun deleteUsers() = userService.deleteUsers()
}