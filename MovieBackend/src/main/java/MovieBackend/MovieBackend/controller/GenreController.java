package MovieBackend.MovieBackend.controller;


import MovieBackend.MovieBackend.dto.GenreDTO;
import MovieBackend.MovieBackend.model.Genre;
import MovieBackend.MovieBackend.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {
    @Autowired
    GenreService service;

    @GetMapping
    public List<GenreDTO>getAllGenres(){
        return service.getAllGenres();
    }

    @PostMapping
    public Genre addGenre(@RequestBody Genre genre){
        return service.addGenre(genre);
    }

    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable Long id){
        service.deleteGenre(id);
    }
}
