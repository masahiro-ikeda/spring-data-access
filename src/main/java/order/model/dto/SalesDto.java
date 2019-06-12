package order.model.dto;

import java.util.ArrayList;

import order.model.entity.SalesHeadEntity;
import order.model.entity.SalesLineEntity;

public class SalesDto {
	private SalesHeadEntity salesHeadEntity;
	private ArrayList<SalesLineEntity> salesLineEntityList;

	public SalesHeadEntity getSalesHeadEntity() {
		return salesHeadEntity;
	}

	public void setSalesHeadEntity(SalesHeadEntity salesHeadEntity) {
		this.salesHeadEntity = salesHeadEntity;
	}

	public void setSalesLineEntity(SalesLineEntity salesLineEntity) {
		// 初期化
		if (salesLineEntityList == null) {
			salesLineEntityList = new ArrayList<>();
		}
		this.salesLineEntityList.add(salesLineEntity);
	}

	public ArrayList<SalesLineEntity> getSalesLineEntityList() {
		return salesLineEntityList;
	}
}
