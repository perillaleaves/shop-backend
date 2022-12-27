package perillaleaves.shop.response;

import java.util.ArrayList;
import java.util.List;

public class PagingResponse {

    private final int number;
    private final int totalPages;
    private final int numberOfElements;

    private List<ItemPagingResponse> content = new ArrayList<>();


    public int getNumber() {
        return number;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public List<ItemPagingResponse> getItemPagingResponses() {
        return content;
    }

    public PagingResponse(int number, int totalPages, int numberOfElements, List<ItemPagingResponse> content) {
        this.number = number;
        this.totalPages = totalPages;
        this.numberOfElements = numberOfElements;
        this.content = content;
    }
}
