package com.gzeinnumer.retrofit.Model;

import com.google.gson.annotations.SerializedName;

public class ResponseUpdateData{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("kode")
	private int kode;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setKode(int kode){
		this.kode = kode;
	}

	public int getKode(){
		return kode;
	}
}