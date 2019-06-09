package order.Model;

import lombok.Data;
import lombok.NonNull;

import java.sql.Timestamp;

@Data
public class SalesLineDto {

    // 売上No
    @NonNull
    private int salesNo;

    // 売上詳細No
    @NonNull
    private int subNo;

    // 注文品
    @NonNull
    private String item;

    // 注文数
    @NonNull
    private int itemNumber;

    // 登録日時
    private Timestamp createdAt;
}
