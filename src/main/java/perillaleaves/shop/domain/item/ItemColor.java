package perillaleaves.shop.domain.item;

import perillaleaves.shop.domain.enumList.Color;

import javax.persistence.*;

@Entity
public class ItemColor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_color_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Enumerated(EnumType.STRING)
    private Color itemColor;


    public Long getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public Color getItemColor() {
        return itemColor;
    }
}
