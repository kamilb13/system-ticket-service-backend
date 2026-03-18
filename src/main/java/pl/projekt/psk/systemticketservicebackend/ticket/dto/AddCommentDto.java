package pl.projekt.psk.systemticketservicebackend.ticket.dto;

public record AddCommentDto(
        Long ticketId,
        String comment
) {
}
