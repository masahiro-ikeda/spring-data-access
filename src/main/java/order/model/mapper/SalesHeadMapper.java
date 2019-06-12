package order.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import order.model.entity.SalesHeadEntity;

@Mapper
public interface SalesHeadMapper {

	/**
	 * 売上ヘッダデータを保存
	 *
	 * @param head
	 */
	void insertHead(SalesHeadEntity head);

	/**
	 * 売上Noの最大値を発行する
	 *
	 * @return 売上Noの最大値
	 */
	int getMaxSalesNo();

	/**
	 * 売上ヘッダデータを全取得
	 *
	 * @return 売上ヘッダの全データ
	 */
	List<SalesHeadEntity> selectAll();
}
