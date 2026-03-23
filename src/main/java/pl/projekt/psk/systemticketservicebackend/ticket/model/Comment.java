package pl.projekt.psk.systemticketservicebackend.ticket.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "COMMENTS")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "CONTENT")
    private String commentContent;
    @Column(name = "USERNAME")
    private String username;
    @ManyToOne
    @JoinColumn(name = "TICKET_ID")
    private Ticket ticket;

    public Comment(Ticket ticket, String commentContent, String username) {
        this.ticket = ticket;
        this.commentContent = commentContent;
        this.username = username;
    }
}
