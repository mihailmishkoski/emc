package emtlab.mk.finki.ukim.model.events;

import emtlab.mk.finki.ukim.model.Book;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ProductCreatedEvent extends ApplicationEvent {

    private Integer bookCopies;

    public ProductCreatedEvent(Book book) {
        super(book);
    }

    public ProductCreatedEvent(Book source, Integer bookCopies) {
        super(source);
        this.bookCopies = bookCopies;
    }

}
