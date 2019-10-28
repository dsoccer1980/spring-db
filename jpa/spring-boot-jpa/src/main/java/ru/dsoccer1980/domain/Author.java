package ru.dsoccer1980.domain;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "author", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"}, name = "author_name")})
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    public Author(String name) {
        this.name = name;
    }
}
