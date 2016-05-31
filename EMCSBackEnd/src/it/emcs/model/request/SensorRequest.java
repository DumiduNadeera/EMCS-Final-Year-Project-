package it.emcs.model.request;

import java.util.ArrayList;

import it.emcs.model.KeyValuePair;

public class SensorRequest {

	String networkId;
	int nodeId;
	ArrayList<KeyValuePair> dataList;

	public String getNetworkId() {
		return networkId;
	}

	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}

	public ArrayList<KeyValuePair> getDataList() {
		return dataList;
	}

	public int getNodeId() {
		return nodeId;
	}

	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}

	public void setDataList(ArrayList<KeyValuePair> dataList) {
		this.dataList = dataList;
	}

}
