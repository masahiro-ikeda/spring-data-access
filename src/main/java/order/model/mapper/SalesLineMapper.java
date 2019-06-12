package order.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import order.model.dto.SalesLineDto;

@Mapper
public interface SalesLineMapper {

	/**
	 * 注文ラインをDBに保存する
	 *
	 * @param line
	 */
	void insertLine(SalesLineDto line);
}
