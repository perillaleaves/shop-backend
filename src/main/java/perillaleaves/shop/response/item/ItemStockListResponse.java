package perillaleaves.shop.response.item;

import java.util.ArrayList;
import java.util.List;

public class ItemStockListResponse {

    private List<ItemListResponse> content = new ArrayList<>();

    public List<ItemListResponse> getContent() {
        return content;
    }

    public ItemStockListResponse(List<ItemListResponse> content) {
        this.content = content;
    }
}
