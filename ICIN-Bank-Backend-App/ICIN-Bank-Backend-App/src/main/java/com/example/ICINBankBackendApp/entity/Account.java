package com.example.ICINBankBackendApp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int id;
//	@Column(unique=true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accno;
//	@Column(name="typeofaccount" )
	private String typeofaccount;
//	@Column(name="emailid")
	private String emailid;
	private float amount;
	public int getAccno() {
		return accno;
	}
	public void setAccno(int accno) {
		this.accno = accno;
	}
	public String getTypeofaccount() {
		return typeofaccount;
	}
	public void setTypeofaccount(String typeofaccount) {
		this.typeofaccount = typeofaccount;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	

}
