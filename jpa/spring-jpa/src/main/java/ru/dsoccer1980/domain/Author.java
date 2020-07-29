package ru.dsoccer1980.domain;


import javax.persistence.*;
import javax.validation.constraints.Size;
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
    @Size(min = 5)
    private String name;

    @Column(name = "toodeldud")
    private String processed;

    public Author(String name) {
        this.name = name;
    }

    public Author(Long id, String name) {
        this.name = name;
        this.id = id;
    }
}
