package foro.hub.api.domain.topic;

import foro.hub.api.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topics")
@Entity(name = "Topic")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private LocalDateTime creationDate;
    private Boolean status;
    private String author;
    private String course;

    public Topic(DataCreateTopic data) {
        this.title = data.title();
        this.message = data.message();
        this.creationDate = LocalDateTime.now();
        this.status = true;
        this.author = data.author();
        this.course = data.course();
    }
}
