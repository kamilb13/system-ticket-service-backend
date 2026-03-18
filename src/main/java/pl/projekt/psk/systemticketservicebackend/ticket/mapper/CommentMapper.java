package pl.projekt.psk.systemticketservicebackend.ticket.mapper;

import pl.projekt.psk.systemticketservicebackend.ticket.dto.CommentDto;
import pl.projekt.psk.systemticketservicebackend.ticket.model.Comment;

import java.util.List;

public class CommentMapper {

    public static List<CommentDto> toCommentDto(List<Comment> allByTicketId) {
        return allByTicketId.stream()
                .map(comment -> new CommentDto(comment.getId(), comment.getCommentContent(), comment.getUsername()))
                .toList();
    }
}
