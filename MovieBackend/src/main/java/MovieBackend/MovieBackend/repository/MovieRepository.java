package MovieBackend.MovieBackend.repository;

import MovieBackend.MovieBackend.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findAllByGenres_Title(String genre);

    List<Movie> findByTitleContainingIgnoreCase(String query);


}
