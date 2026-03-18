package pl.projekt.psk.systemticketservicebackend.ticket.dto;

public record TicketRequest(
        String title,
        String description,
        String category
) {
}