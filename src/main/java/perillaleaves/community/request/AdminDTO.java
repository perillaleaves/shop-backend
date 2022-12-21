package perillaleaves.community.request;

import perillaleaves.community.domain.Rank;

import java.time.LocalDateTime;

public class AdminDTO {

    private String login_id;

    private String password;

    private String name;

    private Rank position;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public String getLogin_id() {
        return login_id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public Rank getPosition() {
        return position;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
