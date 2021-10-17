package ca.mcgill.ecse321.library.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class BookingType {
    Long id;

    BookingType(Long id) {
        this.id = id;
    }

    public BookingType() {
        super();
    }

    @Id
    Long getId() {
        return this.id;
    }

    void setId(Long id) {
        this.id = id;
    }
}