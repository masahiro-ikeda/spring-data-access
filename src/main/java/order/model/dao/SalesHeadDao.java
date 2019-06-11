package order.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import order.model.dto.SalesHeadDto;

@Component
public class SalesHeadDao {

	// application.propertiesで設定したデータソースをDIコンテナから取り出す
	@Autowired
	DataSource dataSource;

	/**
	 * 売上ヘッダデータを保存
	 *
	 * @param head
	 */
	public void insertHead(SalesHeadDto head) throws SQLException {

		// データソースからConnectionを取得
		// ※この方法でConnectionを取得しないと@Transactionalが効きません
		Connection con = DataSourceUtils.getConnection(dataSource);

		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO sales_head ( ");
		builder.append("    sales_no,            ");
		builder.append("    customer_name,       ");
		builder.append("    created_at           ");
		builder.append(") VALUES (               ");
		builder.append("    ?,                   ");
		builder.append("    ?,                   ");
		builder.append("    ?                    ");
		builder.append(")                        ");

		PreparedStatement ps = con.prepareStatement(builder.toString());
		int idx = 0;
		ps.setInt(++idx, head.getSalesNo());
		ps.setString(++idx, head.getName());
		ps.setTimestamp(++idx, new Timestamp(System.currentTimeMillis()));
		ps.executeUpdate();

		// PreparedStatementをクローズする
		// Connectionのクローズはフレームワークが行ってくれるので行わない
		if (ps != null) {
			ps.close();
		}
	}

	/**
	 * 売上Noの最大値を発行する
	 *
	 * @return 売上Noの最大値
	 */
	public int getMaxSalesNo() throws SQLException {

		int maxNo = 0;
		Connection con = DataSourceUtils.getConnection(dataSource);

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT                    ");
		builder.append("  MAX(sales_no) AS max_no ");
		builder.append("FROM                      ");
		builder.append("  sales_head              ");

		PreparedStatement ps = con.prepareStatement(builder.toString());
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			maxNo = rs.getInt("max_no");
		}

		if (ps != null) {
			ps.close();
		}
		if (rs != null) {
			rs.close();
		}

		return maxNo;
	}
}
