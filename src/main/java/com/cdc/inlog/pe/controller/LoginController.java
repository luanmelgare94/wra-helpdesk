package com.cdc.inlog.pe.controller;

import com.cdc.inlog.pe.entity.UsernameEntity;
import com.cdc.inlog.pe.repository.UsernameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/login")
public class LoginController {

    @Autowired
    private UsernameRepository usernameRepository;

    @GetMapping("/isLogin")
    public ResponseEntity<Boolean> isLoginSuccessfully(@RequestParam String usuario, @RequestParam String passwd) {
        UsernameEntity usernameEntity = usernameRepository.findByUsername(usuario);
        if (usernameEntity.getPasswd().equals(passwd)) {
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        }
        return new ResponseEntity<>(Boolean.FALSE, HttpStatus.OK);
    }

}