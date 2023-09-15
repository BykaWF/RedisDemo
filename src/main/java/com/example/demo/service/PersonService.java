package com.example.demo.service;

import com.example.demo.model.Person;
import com.example.demo.repositoty.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PersonService {
    @Autowired
    PersonRepo personRepo;
    public void create(Person person){
        personRepo.set(person);
    }

    public List<Person> getAll() {
        Set<String> keys = personRepo.getAllKeys();
        return keys.stream()
                .map(k -> personRepo.getByKey(k))
                .collect(Collectors.toList());
    }

    public void lpush(Person person) {
        personRepo.lpush(person);
    }

    public List<Person> lpop(long count){
        return personRepo.lpop(count);
    }

    public List<Person> lrange(int start, int end){
        return personRepo.lrange(start,end);
    }

    public void rpush(Person person){
        personRepo.rpush(person);
    }

    public List<Person> rpop(long count){
        return personRepo.rpop(count);
    }
}
