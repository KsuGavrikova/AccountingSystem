package com.senla.training.AccountingSystem;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ThisWillActuallyRun {

    @RequestMapping("/")
    String home() {
        return "<h1>Hello World!</h1>";
    }

}