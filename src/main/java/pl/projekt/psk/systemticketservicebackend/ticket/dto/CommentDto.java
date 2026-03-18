package pl.projekt.psk.systemticketservicebackend.ticket.dto;

public record CommentDto(
        Long id,
        String commentContent,
        String username
) {
}
