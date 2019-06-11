package order.model.mapper;

import order.model.dto.SalesLineDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

@Mapper
public interface SalesLineMapper {

    /**
     * 注文ラインをDBに保存する
     *
     * @param line
     */
    void insertLine(SalesLineDto line);
}
