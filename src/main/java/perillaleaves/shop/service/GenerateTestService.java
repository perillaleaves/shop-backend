package perillaleaves.shop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import perillaleaves.shop.domain.enumList.Color;
import perillaleaves.shop.domain.enumList.Role;
import perillaleaves.shop.domain.item.ItemColor;
import perillaleaves.shop.repository.CartRepository;
import perillaleaves.shop.request.item.ItemDTO;
import perillaleaves.shop.request.user.UserDTO;

@Service
@Transactional
public class GenerateTestService {
    private final UserService userService;
    private final ItemService itemService;
    // admin id
    // product 4 - 10

    public GenerateTestService(UserService userService, ItemService itemService) {
        this.userService = userService;
        this.itemService = itemService;
    }

    public void generate() {
        try {
            userService.save(new UserDTO("12345678", "1234567A", "123", "01045321432", "gggg@gmail.com", Role.BASIC));
            for (int i = 0; i < 10; i++) {
                ItemColor red = itemService.create(new ItemDTO("AAAA" + i, 1000 * i, Color.RED));
                itemService.update(red.getId(), red.getItem().getId(), 10);

                ItemColor blue = itemService.create(new ItemDTO("BBBB" + i, 1000 * i, Color.BLUE));
                itemService.update(blue.getId(), blue.getItem().getId(), 10);
            }

        } catch (Error e) {
            System.out.println("e = " + e);
        }

    }
}
