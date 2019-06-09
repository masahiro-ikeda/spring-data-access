package order.Model;

import lombok.Data;
import lombok.NonNull;

import java.sql.Timestamp;

@Data
public class SalesHeadDto {

    // 売上No
    @NonNull
    private int salesNo;

    // お客名
    @NonNull
    private String name;

    // 登録日時
    private Timestamp createdAt;
}
