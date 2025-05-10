package MovieBackend.MovieBackend.service;

import MovieBackend.MovieBackend.dto.PublisherDTO;
import MovieBackend.MovieBackend.model.Movie;
import MovieBackend.MovieBackend.model.Publisher;
import MovieBackend.MovieBackend.repository.MovieRepository;
import MovieBackend.MovieBackend.repository.PublisherRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PublisherService {
    @Autowired
    PublisherRepository publisherRepository;

    @Autowired
    MovieRepository movieRepository;

    public PublisherDTO getPublisherById(Long id) {
        Publisher publisher = publisherRepository.findById(id).orElseThrow(() -> new RuntimeException("Publisher not found"));
        return new PublisherDTO(publisher.getId(), publisher.getName(), publisher.getContactInfo());
    }

    @Transactional
    public void deletePublisher(Long publisherId) {
        Publisher publisher = publisherRepository.findById(publisherId)
                .orElseThrow(() -> new EntityNotFoundException("Publisher not found"));


        for (Movie movie : publisher.getMovies()) {
            movie.setPublisher(null);
            movieRepository.save(movie);
        }

        publisherRepository.delete(publisher);
    }

    public Publisher addPublisher(Publisher publisher) {
        return publisherRepository.save(publisher);
    }
}
