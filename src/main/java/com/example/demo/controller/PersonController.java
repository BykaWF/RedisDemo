package com.example.demo.controller;

import com.example.demo.dto.CreatePersonRequest;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping("/new")
    public void createPerson(@RequestBody CreatePersonRequest createPersonRequest){
        personService.create(createPersonRequest.toPerson());
    }

    @GetMapping("getAll")
    public List<Person> getAllPerson(){
        return personService.getAll();
    }

    //TODO : getById

    /**
     * List operations
     */


    @PostMapping("/lpush")
    public void createListPerson(@RequestBody @Valid CreatePersonRequest createPersonRequest){
        personService.lpush(createPersonRequest.toPerson());
    }

    @GetMapping("/lrange")
    public List<Person> getAllFromList(
            @RequestParam(name = "start", required = false, defaultValue = "0") int start,
            @RequestParam(name = "end", required = false, defaultValue = "-1") int end
            ){
        return personService.lrange(start,end);
    }


}
