package in.jewelx.jewelxbackend.dto;

import java.util.UUID;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Getter
@Setter
public class UserDto {
	//Brand properties
	private String brandName;
	private String brandDescription;
	private String brandImageUrl;
	
	//User Properties
	private String userName;
	private String email;
	private String mobileNumber;
	private String password;
	private String userRole;
	private UUID subsidiaryId;
	private UUID assignedBy;
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getBrandDescription() {
		return brandDescription;
	}
	public void setBrandDescription(String brandDescription) {
		this.brandDescription = brandDescription;
	}
	public String getBrandImageUrl() {
		return brandImageUrl;
	}
	public void setBrandImageUrl(String brandImageUrl) {
		this.brandImageUrl = brandImageUrl;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public UUID getSubsidiaryId() {
		return subsidiaryId;
	}
	public void setSubsidiaryId(UUID subsidiaryId) {
		this.subsidiaryId = subsidiaryId;
	}
	public UUID getAssignedBy() {
		return assignedBy;
	}
	public void setAssignedBy(UUID assignedBy) {
		this.assignedBy = assignedBy;
	}
	
	
	
	
}
