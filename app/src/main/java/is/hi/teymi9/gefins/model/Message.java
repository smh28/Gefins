package is.hi.teymi9.gefins.model;

import java.util.Date;
import java.util.UUID;

/**
 * Módel klasi fyrir einkaskilaboð (Message)
 *
 * @author Einar
 * @version 1.0
 */


public class Message {

    // unique auðkenni fyrir message
    private UUID id;
    // notandanafn höfunds
    private String from;
    // notandanafna viðtakanda
    private String to;
    // viðfangsefni skilaboða
    private String subject;
    // skilaboðin sjálf
    private String message;
    // dagsetning skilaboða
    private Date date;
    //
    private boolean read;

    // Smiður
    public Message(String from, String to, String subject, String message) {
        id = UUID.randomUUID();
        date = new Date();
        read = false;
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.message = message;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
