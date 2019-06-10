package order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import order.Model.dao.SalesHeadDao;
import order.Model.dao.SalesLineDao;
import order.Model.dto.SalesHeadDto;
import order.Model.dto.SalesLineDto;

@Service
public class OrderService {

	@Autowired
	SalesHeadDao hDao;
	@Autowired
	SalesLineDao lDao;

	@Transactional
	public void executeOrder(OrderForm form) {

		// 売上Noの取得
		int salesNo = hDao.getNextSalesNo();

		// HEADの登録
		SalesHeadDto hDto = new SalesHeadDto();
		hDto.setSalesNo(salesNo);
		hDto.setName(form.getName());
		hDao.insertHead(hDto);

		// LINEの登録
		SalesLineDto orderHamburger = new SalesLineDto();
		orderHamburger.setItem("ハンバーガー");
		orderHamburger.setItemNumber(form.getOrderHamburger());
		orderHamburger.setSalesNo(salesNo);
		orderHamburger.setSubNo(1);
		lDao.insertLine(orderHamburger);

		SalesLineDto orderPotato = new SalesLineDto();
		orderPotato.setItem("ポテト");
		orderPotato.setItemNumber(form.getOrderPotato());
		orderPotato.setSalesNo(salesNo);
		orderPotato.setSubNo(2);
		lDao.insertLine(orderPotato);

		SalesLineDto orderCola = new SalesLineDto();
		orderCola.setItem("コーラ");
		orderCola.setItemNumber(form.getOrderCola());
		orderCola.setSalesNo(salesNo);
		orderCola.setSubNo(3);
		lDao.insertLine(orderCola);
	}
}
