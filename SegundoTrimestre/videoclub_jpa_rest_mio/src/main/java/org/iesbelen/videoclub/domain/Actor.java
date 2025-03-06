package org.iesbelen.videoclub.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="actor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_actor")
    private long idActor;

    private String nombre;
    private String apellidos;

    @ManyToMany(
            mappedBy = "actores")
    @JsonIgnore
    @ToString.Exclude
    Set<Pelicula> peliculas = new HashSet<>();
}
