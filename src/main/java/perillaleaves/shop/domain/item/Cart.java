package perillaleaves.shop.domain.item;

import perillaleaves.shop.common.BaseEntity;
import perillaleaves.shop.domain.user.User;

import javax.persistence.*;

@Entity
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    private int count;

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Item getItem() {
        return item;
    }

    public int getCount() {
        return count;
    }
}
