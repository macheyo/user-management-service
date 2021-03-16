package zw.co.macheyo.usermanagementservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import zw.co.macheyo.usermanagementservice.exception.ResourceNotFoundException;
import zw.co.macheyo.usermanagementservice.model.User;
import zw.co.macheyo.usermanagementservice.repository.UserRepository;
import zw.co.macheyo.usermanagementservice.security.CurrentUser;
import zw.co.macheyo.usermanagementservice.security.UserPrincipal;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}