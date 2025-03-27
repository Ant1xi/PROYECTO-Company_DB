package tablas;

public class Region {
	private Integer regionId;
	private String regionName;
	
	public Region(Integer regionId, String regionName) {
		super();
		this.regionId = regionId;
		this.regionName = regionName;
	}
	
	

	public Integer getRegionId() {
		return regionId;
	}



	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}



	public String getRegionName() {
		return regionName;
	}



	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}



	@Override
	public String toString() {
		return "Region [regionId=" + regionId + ", regionName=" + regionName + "]";
	}
	
}
