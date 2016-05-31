package it.emcs.model;

public class SensorNumber {

	String networkId;
	double value;

	public String getNetworkId() {
		return networkId;
	}

	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "KeyValuePair [networkId=" + networkId + ", value=" + value + "]";
	}

}
