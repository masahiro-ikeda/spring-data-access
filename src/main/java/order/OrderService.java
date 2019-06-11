package order;

import java.sql.SQLException;

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

	/*
	 * @Transactionalを使うと、このメソッド内のDB接続処理をトランザクション管理してくれます。
	 * 途中で例外が起きても自動でロールバックしてくれるので、面倒な処理の記述が不要になります。
	 * ただDIコンテナ上で管理されているデータソースを使う必要があるなどの制約もあります。
	 */
	@Transactional
	public void executeOrder(OrderForm form) throws RuntimeException {

		try {
			// 売上Noの取得
			int salesNo = (hDao.getMaxSalesNo() + 1);

			// HEADの登録
			SalesHeadDto hDto = new SalesHeadDto();
			hDto.setSalesNo(salesNo);
			hDto.setName(form.getName());
			hDao.insertHead(hDto);

			// LINEの登録①
			SalesLineDto orderHamburger = new SalesLineDto();
			orderHamburger.setItem("ハンバーガー");
			orderHamburger.setItemNumber(form.getOrderHamburger());
			orderHamburger.setSalesNo(salesNo);
			orderHamburger.setSubNo(1);
			lDao.insertLine(orderHamburger);

			// LINEの登録②
			SalesLineDto orderPotato = new SalesLineDto();
			orderPotato.setItem("ポテト");
			orderPotato.setItemNumber(form.getOrderPotato());
			orderPotato.setSalesNo(salesNo);
			orderPotato.setSubNo(2);
			lDao.insertLine(orderPotato);

			// LINEの登録③
			SalesLineDto orderCola = new SalesLineDto();
			orderCola.setItem("コーラ");
			orderCola.setItemNumber(form.getOrderCola());
			orderCola.setSalesNo(salesNo);
			orderCola.setSubNo(3);
			lDao.insertLine(orderCola);

		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
}
