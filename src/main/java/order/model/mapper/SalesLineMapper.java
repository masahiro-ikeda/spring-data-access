package order.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import order.model.entity.SalesLineEntity;

@Mapper
public interface SalesLineMapper {

	/**
	 * 注文ラインをDBに保存する
	 *
	 * @param line
	 */
	void insertLine(SalesLineEntity line);

	/**
	 * 売上ラインデータを全取得
	 *
	 * @return 売上ラインの全データ
	 */
	List<SalesLineEntity> selectAll();
}
