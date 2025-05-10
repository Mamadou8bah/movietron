package MovieBackend.MovieBackend.service;

import MovieBackend.MovieBackend.dto.GenreDTO;
import MovieBackend.MovieBackend.model.Genre;
import MovieBackend.MovieBackend.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreService {


    @Autowired
    GenreRepository genreRepository;

    public List<GenreDTO> getAllGenres(){
        List<Genre> genres=genreRepository.findAll();
        List<GenreDTO>genreDTOList=new ArrayList<>();
        for (Genre genre:genres){
            genreDTOList.add(new GenreDTO(genre.getId(), genre.getTitle(), genre.getDescription()));
        }
        return genreDTOList;
    }
    public Genre addGenre(Genre genre){
        genreRepository.save(genre);
        return genre;
    }
    public void deleteGenre(Long id){
        genreRepository.deleteById(id);
    }
}
