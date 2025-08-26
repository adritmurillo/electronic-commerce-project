package com.joaquin.Shop.adapter.in.web;


import com.joaquin.Shop.domain.model.User;
import com.joaquin.Shop.domain.usecase.UserManagementUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserRestAdapter {
    private final UserManagementUseCase userManagementUseCase;

    @Autowired
    public UserRestAdapter(UserManagementUseCase userManagementUseCase) {
        this.userManagementUseCase = userManagementUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User createdUser = userManagementUseCase.createUser(user.getUsername(), user.getPassword());
        return ResponseEntity.status(201).body(createdUser);
    }
}
