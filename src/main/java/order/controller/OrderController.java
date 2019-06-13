package order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import order.form.OrderForm;
import order.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	private OrderService service;

	@RequestMapping("/")
	public String getOrderPage() {

		return "order";
	}

	@PostMapping("/order")
	public String postOrder(@ModelAttribute("form") OrderForm order) {

		try {
			service.executeOrder(order);
			return "thanks";
		} catch (RuntimeException e) {
			return "redirect:/error";
		}
	}
}
