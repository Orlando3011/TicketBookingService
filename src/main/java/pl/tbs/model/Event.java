package pl.tbs.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
    private int ticketsAvailable;
    private int ticketsTotal;
    private Date date;
    private String place;
    private String eventType;
    private float normalTicketPrice;
    private float discountTicketPrice;

    public Event() {}

    public Event(int ticketsAvailable) {
        this.ticketsAvailable = ticketsAvailable;
    }

    public Event(int ticketsAvailable, float normalTicketPrice ) {
        this.ticketsAvailable = ticketsAvailable;
        this.normalTicketPrice = normalTicketPrice ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTicketsAvailable() {
        return ticketsAvailable;
    }

    public void setTicketsAvailable(int ticketsAvailable) {
        this.ticketsAvailable = ticketsAvailable;
    }

    public int getTicketsTotal() {
        return ticketsTotal;
    }

    public void setTicketsTotal(int ticketsTotal) {
        this.ticketsTotal = ticketsTotal;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public float getNormalTicketPrice() {
        return normalTicketPrice;
    }

    public void setNormalTicketPrice(float normalTicketPrice) {
        this.normalTicketPrice = normalTicketPrice;
    }

    public float getDiscountTicketPrice() {
        return discountTicketPrice;
    }

    public void setDiscountTicketPrice(float discountTicketPrice) {
        this.discountTicketPrice = discountTicketPrice;
    }
}
