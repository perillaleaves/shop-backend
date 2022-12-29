package perillaleaves.shop.domain.item;

import perillaleaves.shop.common.BaseEntity;
import perillaleaves.shop.domain.enumList.Kinds;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    @Enumerated(EnumType.STRING)
    private Kinds kind;

    @OneToMany(mappedBy = "item")
    private List<ItemColor> itemColor = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Kinds getKind() {
        return kind;
    }

    public List<ItemColor> getItemColor() {
        return itemColor;
    }

    public Item() {
    }

    public Item(String name, int price, Kinds kind) {
        this.name = name;
        this.price = price;
        this.kind = kind;
    }

}
