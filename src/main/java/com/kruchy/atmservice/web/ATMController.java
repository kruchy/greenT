package com.kruchy.atmservice.web;

import com.kruchy.atmservice.service.ATMService;
import com.kruchy.atmservice.model.ATM;
import com.kruchy.atmservice.model.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/atms")
public class ATMController {

    private final ATMService atmService;

    public ATMController(ATMService atmService) {
        this.atmService = atmService;
    }

    @PostMapping(value = "/calculateOrder", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public ResponseEntity<List<ATM>> calculateOrder(@RequestBody List<Task> tasks) {
        List<ATM> result = atmService.calculateOrder(tasks);
        return ok(result);
    }
}
