package MovieBackend.MovieBackend.service;

import MovieBackend.MovieBackend.dto.MovieDTO;
import MovieBackend.MovieBackend.model.Movie;
import MovieBackend.MovieBackend.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository repo;

    public MovieService(MovieRepository repo) {
        this.repo = repo;
    }

    public List<MovieDTO> getMovies() {
        return repo.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<MovieDTO> getMoviesByGenre(String genre) {
        return repo.findAllByGenres_Title(genre)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    @Transactional
    public MovieDTO addMovie(Movie movie) {
        Movie savedMovie = repo.save(movie);
        return convertToDTO(savedMovie);
    }

    @Transactional
    public MovieDTO updateDescription(Long id, String description) {
        Movie movie = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        movie.setDescription(description);
        Movie updatedMovie = repo.save(movie); // ensure it's saved
        return convertToDTO(updatedMovie);
    }

    public List<MovieDTO> searchMovies(String query) {
        return repo.findByTitleContainingIgnoreCase(query)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteMovie(Long id) {
        Movie movie = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        repo.delete(movie);
    }

    private MovieDTO convertToDTO(Movie movie) {
        return new MovieDTO(
                movie.getId(),
                movie.getTitle(),
                movie.getDescription(),
                movie.getReleasedYear(),
                movie.getRating()
        );
    }
}
