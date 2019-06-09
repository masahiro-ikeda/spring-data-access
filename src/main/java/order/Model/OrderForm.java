package order.Model;

import lombok.Data;
import lombok.NonNull;

@Data
public class OrderForm {
    @NonNull
    private String name;
    @NonNull
    private int orderHamburger;
    @NonNull
    private int orderPotato;
    @NonNull
    private int orderCola;
}
