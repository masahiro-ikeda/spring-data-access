package order.Model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import order.Model.dto.SalesLineDto;

@Component
public class SalesLineDao {

	@Autowired
	DataSource dataSource;

	/**
	 * 注文ラインをDBに保存する
	 *
	 * @param line
	 */
	public void insertLine(SalesLineDto line) throws SQLException {

		Connection con = DataSourceUtils.getConnection(dataSource);

		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO sales_line ( ");
		builder.append("    sales_no,            ");
		builder.append("    sub_no,              ");
		builder.append("    item,                ");
		builder.append("    item_number,         ");
		builder.append("    created_at           ");
		builder.append(") VALUES (               ");
		builder.append("    ?,                   ");
		builder.append("    ?,                   ");
		builder.append("    ?,                   ");
		builder.append("    ?,                   ");
		builder.append("    ?                    ");
		builder.append(")                        ");

		PreparedStatement ps = con.prepareStatement(builder.toString());
		int idx = 0;
		ps.setInt(++idx, line.getSalesNo());
		ps.setInt(++idx, line.getSubNo());
		ps.setString(++idx, line.getItem());
		ps.setInt(++idx, line.getItemNumber());
		ps.setTimestamp(++idx, new Timestamp(System.currentTimeMillis()));
		ps.executeUpdate();

		if (ps != null) {
			ps.close();
		}
	}
}
