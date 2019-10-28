package ru.dsoccer1980.domain;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Author {

    private Long id;
    private String name;

    public Author(String name) {
        this.name = name;
    }
}
