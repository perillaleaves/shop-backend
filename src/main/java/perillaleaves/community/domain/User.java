package perillaleaves.community.domain;

import jakarta.persistence.*;

@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String login_id;

    private String password;

    private String name;

    private String phone_number;

    private String email;


}
