package com.workintech.s19d1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="movie", schema= "fsweb")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name")
    @NotNull(message="film adı boş olamaz")
    private String name;

    @Column(name="director_name")
    @NotNull(message="yönetmen adı boş olamaz")
    private String directorName;

    @Column(name="rating")
    private Integer rating;

    @Column(name="release_date")
    private LocalDate releaseDate;


    //bir film silinince actor silinmeyecek
    //actor silinince de film silinmeyecek
    //cascade type all olmamalı

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name="movie_actor", schema = "fsweb",
            joinColumns = @JoinColumn(name="movie_id"),
            inverseJoinColumns = @JoinColumn(name="actor_id"))
    private List<Actor> actors;

    public void addActor(Actor actor){
        if (actors == null)
            actors = new ArrayList<>();
        actors.add(actor);
    }
}