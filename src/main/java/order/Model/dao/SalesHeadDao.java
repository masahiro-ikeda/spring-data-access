package order.Model.dao;

import order.Model.dto.SalesHeadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class SalesHeadDao {

    @Autowired
    DataSource dataSource;

    /**
     * 売上ヘッダデータを保存
     *
     * @param head
     */
    public void insertHead(SalesHeadDto head) {

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DataSourceUtils.getConnection( dataSource );

            StringBuilder builder = new StringBuilder();
            builder.append( "INSERT INTO sales_head ( " );
            builder.append( "    sales_no,            " );
            builder.append( "    customer_name,       " );
            builder.append( "    created_at           " );
            builder.append( ") VALUES (               " );
            builder.append( "    ?,                   " );
            builder.append( "    ?,                   " );
            builder.append( "    ?                    " );
            builder.append( ")                        " );

            ps = con.prepareStatement( builder.toString() );
            //ps.setInt( 1, head.getSalesNo() );
            //ps.setString( 2, head.getCustomerName() );
            ps.setTimestamp( 3, new Timestamp( System.currentTimeMillis() ) );

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

    /**
     * 次の売上Noを発行する
     *
     * @return 次の売上No
     */
    public int getNextSalesNo() {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int nextNo = 0;
        try {
            con = DataSourceUtils.getConnection( dataSource );

            StringBuilder builder = new StringBuilder();
            builder.append( "SELECT                    " );
            builder.append( "  MAX(sales_no) AS max_no " );
            builder.append( "FROM                      " );
            builder.append( "  sales_head              " );

            ps = con.prepareStatement( builder.toString() );
            rs = ps.executeQuery();

            if (rs.next()) {
                nextNo = rs.getInt( "max_no" );
                nextNo += 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return nextNo;
    }

}
