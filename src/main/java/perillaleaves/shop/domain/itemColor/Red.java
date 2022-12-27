package perillaleaves.shop.domain.itemColor;

import perillaleaves.shop.domain.Item;

import javax.persistence.*;

@Entity
public class Red {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "red_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Item item;

    public Long getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public Red() {
    }

    public Red(Long id, Item item) {
        this.id = id;
        this.item = item;
    }
}
