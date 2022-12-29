package perillaleaves.shop.domain.item;

import perillaleaves.shop.common.BaseEntity;

import javax.persistence.*;

@Entity
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String item_code;

    private String name;

    private int price;

    public Long getId() {
        return id;
    }

    public String getItem_code() {
        return item_code;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }


    public Item() {
    }

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

}
