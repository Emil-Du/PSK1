package lab1.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "STUDIO")
public class Studio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "STUDIO_NAME", nullable = false) // Pavyzdys: laukas name, stulpelis STUDIO_NAME
    private String name;

    @OneToMany(mappedBy = "studio", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Movie> movies = new ArrayList<>();

    public Studio() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}