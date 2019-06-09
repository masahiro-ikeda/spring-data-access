package order;

import order.Model.OrderForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrderController {

    @RequestMapping("/")
    public String getOrderPage(){
        return "order";
    }

    @PostMapping("/order")
    public String postOrder(@ModelAttribute OrderForm order){

    }
}
