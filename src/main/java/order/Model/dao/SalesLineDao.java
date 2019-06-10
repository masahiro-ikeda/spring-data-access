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
	public void insertLine(SalesLineDto line) {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataSourceUtils.getConnection(dataSource);

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

			ps = con.prepareStatement(builder.toString());
			ps.setInt(1, line.getSalesNo());
			ps.setInt(2, line.getSubNo());
			ps.setString(3, line.getItem());
			ps.setInt(4, line.getItemNumber());
			ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
