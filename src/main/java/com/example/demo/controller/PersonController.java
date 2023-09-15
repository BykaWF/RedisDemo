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

    /**
     *
     * @param count by default is 1, if you want to delete more than 1 element, increase the count
     * @return Removes and returns the first elements of the list stored at key.
     */
    @DeleteMapping("/lpop")
    public List<Person> deleteLeftPop(@RequestParam(name = "count", required = false, defaultValue = "1") long count){
        return personService.lpop(count);
    }

    /**
     * Time Complexity : O(1) for each element added, so O(N) to add N elements when the command is called with multiple arguments.
     * @param createPersonRequest
     * Insert all the specified values at the tail of the list stored at key. If key does not exist, it is created as empty list before performing the push operation.
     */
    @PostMapping("/rpush")
    public void createListPersonRight(@RequestBody CreatePersonRequest createPersonRequest){
        personService.rpush(createPersonRequest.toPerson());
    }

    /**
     *
     * @param count By default, the command pops a single element from the end of the list. When provided with the optional count argument, the reply will consist of up to count elements, depending on the list's length.
     * @return Removes and returns the last elements of the list stored at key.
     */
    @DeleteMapping("/rpop")
    public List<Person> deleteRightPop(@RequestParam(name = "count", required = false, defaultValue = "1") long count){
        return personService.rpop(count);
    }
}
