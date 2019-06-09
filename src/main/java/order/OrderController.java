package order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrderController {

    @Autowired
    private OrderService service;

    @RequestMapping("/")
    public String getOrderPage() {

        return "order";
    }

    @PostMapping("/order")
    public String postOrder(@ModelAttribute OrderForm order) {

        service.executeOrder( order );
        return "thanks";
    }
}
