package MovieBackend.MovieBackend.repository;

import MovieBackend.MovieBackend.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre,Long> {
}
