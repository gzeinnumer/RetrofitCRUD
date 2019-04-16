package com.gzeinnumer.retrofit.Model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseReadData{

	@SerializedName("result_read_data")
	private List<ResultReadDataItem> resultReadData;

	@SerializedName("kode")
	private int kode;

	public void setResultReadData(List<ResultReadDataItem> resultReadData){
		this.resultReadData = resultReadData;
	}

	public List<ResultReadDataItem> getResultReadData(){
		return resultReadData;
	}

	public void setKode(int kode){
		this.kode = kode;
	}

	public int getKode(){
		return kode;
	}
}