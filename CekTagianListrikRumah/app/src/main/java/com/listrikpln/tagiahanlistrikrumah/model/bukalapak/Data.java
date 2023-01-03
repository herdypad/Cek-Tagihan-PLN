package com.listrikpln.tagiahanlistrikrumah.model.bukalapak;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data{

	@SerializedName("admin_charge")
	private int adminCharge;

	@SerializedName("period")
	private List<String> period;

	@SerializedName("amount")
	private int amount;

	@SerializedName("customer_number")
	private String customerNumber;

	@SerializedName("outstanding_bill")
	private int outstandingBill;

	@SerializedName("reference_number")
	private Object referenceNumber;

	@SerializedName("stand_meter")
	private String standMeter;

	@SerializedName("unpaid_bill")
	private int unpaidBill;

	@SerializedName("partner")
	private Partner partner;

	@SerializedName("segmentation")
	private String segmentation;

	@SerializedName("penalty_fee")
	private int penaltyFee;

	@SerializedName("bills")
	private List<BillsItem> bills;

	@SerializedName("customer_name")
	private String customerName;

	@SerializedName("power")
	private int power;

	public int getAdminCharge(){
		return adminCharge;
	}

	public List<String> getPeriod(){
		return period;
	}

	public int getAmount(){
		return amount;
	}

	public String getCustomerNumber(){
		return customerNumber;
	}

	public int getOutstandingBill(){
		return outstandingBill;
	}

	public Object getReferenceNumber(){
		return referenceNumber;
	}

	public String getStandMeter(){
		return standMeter;
	}

	public int getUnpaidBill(){
		return unpaidBill;
	}

	public Partner getPartner(){
		return partner;
	}

	public String getSegmentation(){
		return segmentation;
	}

	public int getPenaltyFee(){
		return penaltyFee;
	}

	public List<BillsItem> getBills(){
		return bills;
	}

	public String getCustomerName(){
		return customerName;
	}

	public int getPower(){
		return power;
	}
}