package order.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import order.form.OrderForm;
import order.model.dto.SalesDto;
import order.model.entity.SalesHeadEntity;
import order.model.entity.SalesLineEntity;
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
			SalesHeadEntity hDto = new SalesHeadEntity();
			hDto.setSalesNo(salesNo);
			hDto.setCustomerName(form.getName());
			hDto.setCreatedAt(new Timestamp(System.currentTimeMillis()));
			hMapper.insertHead(hDto);

			// LINEの登録①
			SalesLineEntity orderHamburger = new SalesLineEntity();
			orderHamburger.setItem("ハンバーガー");
			orderHamburger.setItemNumber(form.getOrderHamburger());
			orderHamburger.setSalesNo(salesNo);
			orderHamburger.setSubNo(1);
			orderHamburger.setCreatedAt(new Timestamp(System.currentTimeMillis()));
			lMapper.insertLine(orderHamburger);

			// LINEの登録②
			SalesLineEntity orderPotato = new SalesLineEntity();
			orderPotato.setItem("ポテト");
			orderPotato.setItemNumber(form.getOrderPotato());
			orderPotato.setSalesNo(salesNo);
			orderPotato.setSubNo(2);
			orderPotato.setCreatedAt(new Timestamp(System.currentTimeMillis()));
			lMapper.insertLine(orderPotato);

			// LINEの登録③
			SalesLineEntity orderCola = new SalesLineEntity();
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

	public List<SalesDto> getOrderList() throws RuntimeException {

		List<SalesDto> salesList = new ArrayList<>();

		try {
			List<SalesHeadEntity> hList = hMapper.selectAll();
			List<SalesLineEntity> lList = lMapper.selectAll();

			for (SalesHeadEntity head : hList) {
				SalesDto dto = new SalesDto();
				dto.setSalesHeadEntity(head);

				for (SalesLineEntity line : lList) {
					if (head.getSalesNo() == line.getSalesNo()) {
						dto.setSalesLineEntity(line);
					}
				}
				salesList.add(dto);
			}
		} catch (DataAccessException e) {
			throw new RuntimeException();
		}

		return salesList;
	}
}
