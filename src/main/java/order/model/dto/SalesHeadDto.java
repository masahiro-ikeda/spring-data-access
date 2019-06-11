package order.model.dto;

import java.sql.Timestamp;

public class SalesHeadDto {

	// 売上No
	private int salesNo;
	// お客名
	private String name;
	// 登録日時
	private Timestamp createdAt;

	public int getSalesNo() {
		return salesNo;
	}

	public void setSalesNo(int salesNo) {
		this.salesNo = salesNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
}
