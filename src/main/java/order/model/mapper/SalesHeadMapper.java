package order.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import order.model.dto.SalesHeadDto;

@Mapper
public interface SalesHeadMapper {

	/**
	 * 売上ヘッダデータを保存
	 *
	 * @param head
	 */
	void insertHead(SalesHeadDto head);

	/**
	 * 売上Noの最大値を発行する
	 *
	 * @return 売上Noの最大値
	 */
	int getMaxSalesNo();
}
