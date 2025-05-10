package MovieBackend.MovieBackend.controller;

import MovieBackend.MovieBackend.dto.MovieDTO;
import MovieBackend.MovieBackend.model.Movie;
import MovieBackend.MovieBackend.service.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService service;

    @GetMapping
    public List<MovieDTO> getAllMovies() {
        return service.getMovies();
    }

    @GetMapping("/genre/{genre}")
    public List<MovieDTO> getMoviesByGenre(@PathVariable String genre) {
        return service.getMoviesByGenre(genre);
    }

    @PostMapping
    public MovieDTO createMovie(@RequestBody Movie movie) {
        return service.addMovie(movie);
    }

    @PutMapping("/{id}")
    public MovieDTO updateMovie(@PathVariable Long id, @RequestBody MovieDTO movieDTO) {
        return service.updateDescription(id, movieDTO.getDescription());
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
        service.deleteMovie(id);
    }
}
