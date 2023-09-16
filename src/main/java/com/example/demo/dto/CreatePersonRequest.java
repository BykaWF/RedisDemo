package com.example.demo.dto;

import com.example.demo.model.Address;
import com.example.demo.model.Person;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePersonRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String age;


    public Person toPerson(){
        return Person.builder()
                .age(this.age)
                .name(this.name)
                .id(UUID.randomUUID().toString())
                .build();
    }
}
