package perillaleaves.shop.domain.item;

import perillaleaves.shop.common.BaseEntity;
import perillaleaves.shop.domain.enumList.Color;

import javax.persistence.*;

@Entity
public class ItemColor extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "color_id")
    private Long id;

    private int stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Enumerated(EnumType.STRING)
    private Color color;

    public Long getId() {
        return id;
    }

    public int getStock() {
        return stock;
    }

    public Item getItem() {
        return item;
    }

    public Color getColor() {
        return color;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public ItemColor() {
    }

    public ItemColor(Item item, Color color) {
        this.item = item;
        this.color = color;
    }
}
