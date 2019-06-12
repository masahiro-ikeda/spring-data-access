package order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import order.form.OrderForm;
import order.model.dto.SalesDto;
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
	public String postOrder(@ModelAttribute OrderForm order) {

		try {
			service.executeOrder(order);
			return "thanks";
		} catch (RuntimeException e) {
			return "redirect:/error";
		}
	}

	@RequestMapping("/list")
	public ModelAndView getOrderList(ModelAndView mv) {
		mv.setViewName("list");
		List<SalesDto> list = service.getOrderList();
		mv.addObject("list", list);
		return mv;
	}
}
