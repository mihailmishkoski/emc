package emtlab.mk.finki.ukim.listeners;

import emtlab.mk.finki.ukim.model.events.ProductCreatedEvent;
import emtlab.mk.finki.ukim.service.BookService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ProductEventHandler {
    private final BookService bookService;

    public ProductEventHandler(BookService bookService) {
        this.bookService = bookService;
    }
    @EventListener
    public void onProductEvent(ProductCreatedEvent event)
    {
        System.out.println("Knigata shto se dodade ima "+ event.getBookCopies() + " kopii");
    }
}
