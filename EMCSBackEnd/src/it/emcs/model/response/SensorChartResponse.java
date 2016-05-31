package it.emcs.model.response;

public class SensorChartResponse {

	String networkId;
	int nodeId;
	String checkedTime;
	String key;
	double value;

	public String getNetworkId() {
		return networkId;
	}

	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}

	public int getNodeId() {
		return nodeId;
	}

	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getCheckedTime() {
		return checkedTime;
	}

	public void setCheckedTime(String checkedTime) {
		this.checkedTime = checkedTime;
	}

}
