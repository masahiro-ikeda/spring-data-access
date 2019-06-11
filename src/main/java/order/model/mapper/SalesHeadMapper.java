package order.model.mapper;

import order.model.dto.SalesHeadDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SalesHeadMapper {

	/**
	 * 売上ヘッダデータを保存
	 *
	 * @param head
	 */
	//@Insert("INSERT INTO sales_head (sales_no, customer_name, created_at ) VALUES (#{salesNo}, #{name}, #{createdAt})")
	void insertHead(SalesHeadDto head);

	/**
	 * 売上Noの最大値を発行する
	 *
	 * @return 売上Noの最大値
	 */
	//@Select("SELECT MAX(sales_no) AS max_no FROM sales_head")
	int getMaxSalesNo();
}
