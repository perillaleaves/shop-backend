package perillaleaves.shop.domain.item;


import javax.persistence.*;

@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemColor_id")
    private ItemColor itemColor;


    private int count;

    public Long getId() {
        return id;
    }

    public Cart getCart() {
        return cart;
    }

    public ItemColor getItemColor() {
        return itemColor;
    }

    public int getCount() {
        return count;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setItemColor(ItemColor itemColor) {
        this.itemColor = itemColor;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public CartItem() {
    }

    public CartItem(Cart cart, ItemColor itemColor, int count) {
        this.cart = cart;
        this.itemColor = itemColor;
        this.count = count;
    }
}
