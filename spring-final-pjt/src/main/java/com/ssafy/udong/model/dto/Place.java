package com.ssafy.udong.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Place {
	private int placeId;
	
	private String hashtag;
	
	private int cnt = 0;
	
	private double distance;
	
	@JsonProperty("공원명")
	private String placeName;
	
	@JsonProperty("설치년도")
	private int eqYear;
	
	@JsonProperty("설치주소")
	private String address;
	
	private String addressGu;     //설치주소(구)
	private String addressDong;   //설치주소(동)
	private String addressDetail; //설치주소(상세)
	
	@JsonProperty("설치운동기구종류")
	private String eqKind;
	
	@JsonProperty("연간운동기구관리횟수")
	private String eqFix;
	
	@JsonProperty("고장난운동기구명")
	private String eqBroken;
	
	@JsonProperty("위도")
	private double latitude;
	
	@JsonProperty("경도")
	private double longitude;
	
	@JsonProperty("담당부서명")
	private String dept;
	
	@JsonProperty("담당부서전화번호")
	private String deptPhone;
	
	@JsonProperty("데이터기준일자")
	private Date updateDate;
	
	//기본생성자
	public Place() {}

	public Place(int placeId, String hashtag, int cnt, double distance, String placeName, int eqYear, String address,
			String addressGu, String addressDong, String addressDetail, String eqKind, String eqFix, String eqBroken,
			double latitude, double longitude, String dept, String deptPhone, Date updateDate) {
		super();
		this.placeId = placeId;
		this.hashtag = hashtag;
		this.cnt = cnt;
		this.distance = distance;
		this.placeName = placeName;
		this.eqYear = eqYear;
		this.address = address;
		this.addressGu = addressGu;
		this.addressDong = addressDong;
		this.addressDetail = addressDetail;
		this.eqKind = eqKind;
		this.eqFix = eqFix;
		this.eqBroken = eqBroken;
		this.latitude = latitude;
		this.longitude = longitude;
		this.dept = dept;
		this.deptPhone = deptPhone;
		this.updateDate = updateDate;
	}

	public int getPlaceId() {
		return placeId;
	}

	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}

	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public int getEqYear() {
		return eqYear;
	}

	public void setEqYear(int eqYear) {
		this.eqYear = eqYear;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressGu() {
		return addressGu;
	}

	public void setAddressGu(String addressGu) {
		this.addressGu = addressGu;
	}

	public String getAddressDong() {
		return addressDong;
	}

	public void setAddressDong(String addressDong) {
		this.addressDong = addressDong;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getEqKind() {
		return eqKind;
	}

	public void setEqKind(String eqKind) {
		this.eqKind = eqKind;
	}

	public String getEqFix() {
		return eqFix;
	}

	public void setEqFix(String eqFix) {
		this.eqFix = eqFix;
	}

	public String getEqBroken() {
		return eqBroken;
	}

	public void setEqBroken(String eqBroken) {
		this.eqBroken = eqBroken;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getDeptPhone() {
		return deptPhone;
	}

	public void setDeptPhone(String deptPhone) {
		this.deptPhone = deptPhone;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "Place [placeId=" + placeId + ", hashtag=" + hashtag + ", cnt=" + cnt + ", distance=" + distance
				+ ", placeName=" + placeName + ", eqYear=" + eqYear + ", address=" + address + ", addressGu="
				+ addressGu + ", addressDong=" + addressDong + ", addressDetail=" + addressDetail + ", eqKind=" + eqKind
				+ ", eqFix=" + eqFix + ", eqBroken=" + eqBroken + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", dept=" + dept + ", deptPhone=" + deptPhone + ", updateDate=" + updateDate + "]";
	}

	
	

}
