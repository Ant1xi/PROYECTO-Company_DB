package tablas;

public class Warehouse {
	private Integer warehouseId;
	private String warehouseName;
	private Integer locationId;

	public Warehouse(Integer warehouseId, String warehouseName, Integer locationId) {
		super();
		this.warehouseId = warehouseId;
		this.warehouseName = warehouseName;
		this.locationId = locationId;
	}

	public Integer getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	@Override
	public String toString() {
		return "Warehouse [warehouseId=" + warehouseId + ", warehouseName=" + warehouseName + ", locationId="
				+ locationId + "]";
	}
	
	
}
