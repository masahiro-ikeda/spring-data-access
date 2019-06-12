package order;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import order.model.dto.SalesHeadDto;
import order.model.dto.SalesLineDto;
import order.model.mapper.SalesHeadMapper;
import order.model.mapper.SalesLineMapper;

@Service
public class OrderService {

	@Autowired
	SalesHeadMapper hMapper;
	@Autowired
	SalesLineMapper lMapper;

	/*
	 * @Transactionalを使うと、このメソッド内のDB接続処理をトランザクション管理してくれます。
	 * 途中で例外が起きても自動でロールバックしてくれるので、面倒な処理の記述が不要になります。
	 * ただDIコンテナ上で管理されているデータソースを使う必要があるなどの制約もあります。
	 */
	@Transactional
	public void executeOrder(OrderForm form) throws RuntimeException {

		try {
			// 売上Noの取得
			int salesNo = (hMapper.getMaxSalesNo() + 1);

			// HEADの登録
			SalesHeadDto hDto = new SalesHeadDto();
			hDto.setSalesNo(salesNo);
			hDto.setName(form.getName());
			hDto.setCreatedAt(new Timestamp(System.currentTimeMillis()));
			hMapper.insertHead(hDto);

			// LINEの登録①
			SalesLineDto orderHamburger = new SalesLineDto();
			orderHamburger.setItem("ハンバーガー");
			orderHamburger.setItemNumber(form.getOrderHamburger());
			orderHamburger.setSalesNo(salesNo);
			orderHamburger.setSubNo(1);
			orderHamburger.setCreatedAt(new Timestamp(System.currentTimeMillis()));
			lMapper.insertLine(orderHamburger);

			// LINEの登録②
			SalesLineDto orderPotato = new SalesLineDto();
			orderPotato.setItem("ポテト");
			orderPotato.setItemNumber(form.getOrderPotato());
			orderPotato.setSalesNo(salesNo);
			orderPotato.setSubNo(2);
			orderPotato.setCreatedAt(new Timestamp(System.currentTimeMillis()));
			lMapper.insertLine(orderPotato);

			// LINEの登録③
			SalesLineDto orderCola = new SalesLineDto();
			orderCola.setItem("コーラ");
			orderCola.setItemNumber(form.getOrderCola());
			orderCola.setSalesNo(salesNo);
			orderCola.setSubNo(3);
			orderCola.setCreatedAt(new Timestamp(System.currentTimeMillis()));
			lMapper.insertLine(orderCola);

		} catch (DataAccessException e) {
			throw new RuntimeException();
		}
	}
}
