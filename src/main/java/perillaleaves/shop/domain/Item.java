package perillaleaves.community.domain;

import perillaleaves.community.common.BaseEntity;

import javax.persistence.*;

@Entity
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    private int stock;

    @Enumerated(EnumType.STRING)
    private Kinds kind;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public Kinds getKind() {
        return kind;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Item() {
    }

    public Item(String name, int price, int stock, Kinds kind) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.kind = kind;
    }

}
