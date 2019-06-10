package order;

public class OrderForm {
	private String name;
	private int orderHamburger;
	private int orderPotato;
	private int orderCola;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOrderHamburger() {
		return orderHamburger;
	}

	public void setOrderHamburger(int orderHamburger) {
		this.orderHamburger = orderHamburger;
	}

	public int getOrderPotato() {
		return orderPotato;
	}

	public void setOrderPotato(int orderPotato) {
		this.orderPotato = orderPotato;
	}

	public int getOrderCola() {
		return orderCola;
	}

	public void setOrderCola(int orderCola) {
		this.orderCola = orderCola;
	}
}
