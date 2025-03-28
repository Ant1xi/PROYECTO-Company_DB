package tablas;

public class Inventory {
	private Integer productId;
	private Integer warehouseId;
	private Integer quantity;

	public Inventory(Integer productId, Integer warehouseId, Integer quantity) {
		super();
		this.productId = productId;
		this.warehouseId = warehouseId;
		this.quantity = quantity;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Inventory [productId=" + productId + ", warehouseId=" + warehouseId + ", quantity=" + quantity + "]";
	}

}
