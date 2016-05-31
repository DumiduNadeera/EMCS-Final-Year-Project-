package it.emcs.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document()
public class SensorResponse {

	@Id
	private String id;

	String networkId;
	int nodeId;
	Date date;
	String key;
	double value;

	public SensorResponse(String networkId, int nodeId, Date date, String key, double value) {
		super();
		this.networkId = networkId;
		this.nodeId = nodeId;
		this.date = date;
		this.key = key;
		this.value = value;
	}

	public String getNetworkId() {
		return networkId;
	}

	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public int getNodeId() {
		return nodeId;
	}

	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}

	@Override
	public String toString() {
		return "SensorResponse [id=" + id + ", networkId=" + networkId + ", date=" + date + ", key=" + key + ", value="
				+ value + "]";
	}
}
