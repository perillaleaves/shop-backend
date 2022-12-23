package perillaleaves.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootApplication
@EnableJpaAuditing
public class CommunityApplication {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void started() {
        for (int i = 0; i < 20; i++) {

        }
    }


    public static void main(String[] args) {
        SpringApplication.run(CommunityApplication.class, args);
    }

}
