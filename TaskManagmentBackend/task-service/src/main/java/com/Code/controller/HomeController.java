package com.Code.controller;


import com.Code.modal.Task;
import com.Code.modal.TaskStatus;
import com.Code.modal.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    @GetMapping("/tasks")
    public ResponseEntity<String> getGreadingMessage()  {

        return new ResponseEntity<>("Welcome to task Service", HttpStatus.OK);
    }

}
