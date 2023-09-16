package com.example.demo.repositoty;

import com.example.demo.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Repository
public class PersonRepo {

    private static final String PERSON_KEY_PREFIX = "person:: ";
    private static final String PERSON_L = "personL:: ";
    private static final String PERSON_H ="person_hash:: ";
    private static final Integer PERSON_VALUE_EXPIRE = 1;
    @Autowired
    RedisTemplate<String, Person> redisTemplate;
    @Autowired
    ObjectMapper objectMapper;

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

    /**
     * Time complexity: O(N) where N is the number of elements returned
     * @return  Removes and returns the first elements of the list stored at key.
     *
     * By default, the command pops a single element from the beginning of the list.
     * When provided with the optional count argument, the reply will consist of up to count elements, depending on the list's length.
     */
    public List<Person> lpop(long count) {
        return this.redisTemplate.opsForList().leftPop(PERSON_L, count);
    }

    public void rpush(Person person){
        this.redisTemplate.opsForList().rightPush(PERSON_L,person);
    }
    public List<Person> rpop(long count){
        return this.redisTemplate.opsForList().rightPop(PERSON_L,count);
    }

    public List<Person> lrange(int start, int end){
        return this.redisTemplate.opsForList().range(PERSON_L,start,end);
    }

    // HASH OPERATIONS

    /**
     * Sets the specified fields to their respective values in the hash stored at key.
     *
     * This command overwrites the values of specified fields that exist in the hash. If key doesn't exist, a new key holding a hash is created.
     * @param person
     */
    public void hset(Person person){
        Map map = objectMapper.convertValue(person,Map.class);
        this.redisTemplate.opsForHash().putAll(getKeyForHash(person.getId()),map);
    }

    public Person hgetAll(String personId){
        Map map = this.redisTemplate.opsForHash().entries(getKeyForHash(personId));
        return objectMapper.convertValue(map,Person.class);
    }

    String getKeyForHash(String personId) {
        return PERSON_H + personId;
    }

}
