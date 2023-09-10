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
    private String age;
    private String id;

    public Person toPerson(){
        return Person.builder()
                .age(this.age)
                .id(this.id)
                .name(this.name)
                .build();
    }
}
