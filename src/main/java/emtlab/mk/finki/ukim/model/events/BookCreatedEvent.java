package emtlab.mk.finki.ukim.model.events;

import emtlab.mk.finki.ukim.model.Book;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;
import java.time.LocalDateTime;

@Getter
public class BookCreatedEvent extends ApplicationEvent {

    private LocalDateTime when;

    public BookCreatedEvent(Book source) {
        super(source);
        this.when = LocalDateTime.now();
    }

    public BookCreatedEvent(Book source, LocalDateTime when) {
        super(source);
        this.when = when;
    }
}
