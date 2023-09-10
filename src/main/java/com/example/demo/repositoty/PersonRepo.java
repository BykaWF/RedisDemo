package com.example.demo.repositoty;

import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Repository
public class PersonRepo {

    private static final String PERSON_KEY_PREFIX = "person:: ";
    private static final String PERSON_L = "personL:: ";
    private static final Integer PERSON_VALUE_EXPIRE = 1;
    @Autowired
    RedisTemplate<String, Person> redisTemplate;

    public void set(Person person){
        String key = getKey(person.getId());
        this.redisTemplate.opsForValue().set(key,person,PERSON_VALUE_EXPIRE, TimeUnit.DAYS);
    }

    public String getKey(String personId){
        return PERSON_KEY_PREFIX + personId;
    }

    public Set<String> getAllKeys() {
        return this.redisTemplate.keys(PERSON_KEY_PREFIX + "*");
    }

    public Person getByKey(String key){
        return this.redisTemplate.opsForValue().get(key);
    }

    public void lpush(Person person) {
        this.redisTemplate.opsForList().leftPush(PERSON_L,person);
    }

    public void lpop() {
        this.redisTemplate.opsForList().leftPop(PERSON_L);
    }

    public void rpush(Person person){
        this.redisTemplate.opsForList().rightPush(PERSON_L,person);
    }
    public void rpop(){
        this.redisTemplate.opsForList().rightPop(PERSON_L);
    }

    public List<Person> lrange(int start, int end){
        return this.redisTemplate.opsForList().range(PERSON_L,start,end);
    }


}
