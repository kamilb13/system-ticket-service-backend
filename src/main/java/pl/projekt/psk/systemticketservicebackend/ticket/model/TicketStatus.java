package pl.projekt.psk.systemticketservicebackend.ticket.model;

public enum TicketStatus {
    NEW("Nowe"),
    IN_PROGRESS("W realizacji"),
    RESOLVED("Rozwiązane"),
    CLOSED("Zamknięte");

    private final String displayName;

    TicketStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
