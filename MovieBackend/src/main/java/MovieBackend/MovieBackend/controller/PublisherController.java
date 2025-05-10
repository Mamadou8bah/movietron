package MovieBackend.MovieBackend.controller;

import MovieBackend.MovieBackend.dto.PublisherDTO;
import MovieBackend.MovieBackend.model.Publisher;
import MovieBackend.MovieBackend.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publisher")
public class PublisherController {

    @Autowired
    PublisherService service;

    @GetMapping("/{id}")
    public PublisherDTO getPublisherById(@PathVariable Long id){
        return service.getPublisherById(id);
    }

    @PostMapping
    public Publisher addPublisher(@RequestBody Publisher publisher){
        return service.addPublisher(publisher);
    }

    @DeleteMapping("/{id}")
    public void deletePublisher(@PathVariable Long id){
        service.deletePublisher(id);
    }


}
