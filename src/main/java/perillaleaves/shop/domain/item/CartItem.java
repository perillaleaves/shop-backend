package perillaleaves.shop.domain.item;


import javax.persistence.*;

@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_im")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    private int count;

    public Long getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public Cart getCart() {
        return cart;
    }

    public int getCount() {
        return count;
    }

    public CartItem() {
    }

    public CartItem(Long id, Item item, Cart cart) {
        this.id = id;
        this.item = item;
        this.cart = cart;
    }
}
