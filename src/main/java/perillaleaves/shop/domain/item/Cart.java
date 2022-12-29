package perillaleaves.shop.domain.item;

import perillaleaves.shop.common.BaseEntity;
import perillaleaves.shop.domain.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy= "cart", cascade = CascadeType.ALL, orphanRemoval= true)
    private List<CartItem> cartItems = new ArrayList<CartItem>();


    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

}
