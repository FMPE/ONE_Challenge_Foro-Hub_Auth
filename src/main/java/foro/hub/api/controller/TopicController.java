package foro.hub.api.controller;

import foro.hub.api.domain.topic.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @PostMapping
    public ResponseEntity<DataResponseTopic> registrarTopico(@RequestBody @Valid DataCreateTopic dataCreateTopic,
            UriComponentsBuilder uriComponentsBuilder) {
        Topic topic = topicRepository.save(new Topic(dataCreateTopic));
        DataResponseTopic dataResponseTopic = new DataResponseTopic(topic.getId(), topic.getTitle(), topic.getMessage(),
                topic.getCreationDate(), topic.getStatus(), topic.getAuthor(), topic.getCourse());

        URI url = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(url).body(dataResponseTopic);
    }

    @GetMapping
    public ResponseEntity<Page<DataListTopic>> listadoTopicos(@PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(topicRepository.findAll(paginacion).map(DataListTopic::new));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        Topic topic = topicRepository.getReferenceById(id);
        topicRepository.delete(topic);
        return ResponseEntity.noContent().build();
    }
}
