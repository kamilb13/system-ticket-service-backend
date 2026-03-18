package pl.projekt.psk.systemticketservicebackend.ticket.exceptions;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(String message) {
        super(message);
    }
}
