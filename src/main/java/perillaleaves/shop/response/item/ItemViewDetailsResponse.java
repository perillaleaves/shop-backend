package perillaleaves.shop.response.item;

import java.util.ArrayList;
import java.util.List;

public class ItemViewDetailsResponse {

    private final Long item_id;
    private final String name;
    private final int price;
    private List<ItemColorListResponse> itemColorList = new ArrayList<>();

    public Long getItem_id() {
        return item_id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public List<ItemColorListResponse> getItemColorList() {
        return itemColorList;
    }

    public ItemViewDetailsResponse(Long item_id, String name, int price, List<ItemColorListResponse> itemColorList) {
        this.item_id = item_id;
        this.name = name;
        this.price = price;
        this.itemColorList = itemColorList;
    }
}
