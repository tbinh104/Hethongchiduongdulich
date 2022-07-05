package com.example.DB_Map.Model;

import java.sql.Date;
import java.time.LocalDate;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "lichsu")
public class Lichsu {
	@Id
	@Column(name = "ls_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int lsid;
	@Column(name = "ls_thoigian")
	String lsthoigian;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ls_dddl_id")
	private Diadiemdulich diadiemdulich;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ls_tk_id")
	private Taikhoan taikhoan;
	
	public Lichsu() {
		super();
	}

	public Lichsu(int lsid, String lsthoigian, Diadiemdulich diadiemdulich, Taikhoan taikhoan) {
		super();
		this.lsid = lsid;
		this.lsthoigian = lsthoigian;
		this.diadiemdulich = diadiemdulich;
		this.taikhoan = taikhoan;
	}
	

	public Lichsu(String lsthoigian, Diadiemdulich diadiemdulich, Taikhoan taikhoan) {
		super();
		this.lsthoigian = lsthoigian;
		this.diadiemdulich = diadiemdulich;
		this.taikhoan = taikhoan;
	}

	public int getLsid() {
		return lsid;
	}

	public void setLsid(int lsid) {
		this.lsid = lsid;
	}

	public String getLsthoigian() {
		return lsthoigian;
	}

	public void setLsthoigian(String localDate) {
		this.lsthoigian = localDate;
	}

	public Diadiemdulich getDiadiemdulich() {
		return diadiemdulich;
	}

	public void setDiadiemdulich(Diadiemdulich diadiemdulich) {
		this.diadiemdulich = diadiemdulich;
	}

	public Taikhoan getTaikhoan() {
		return taikhoan;
	}

	public void setTaikhoan(Taikhoan taikhoan) {
		this.taikhoan = taikhoan;
	}


	
	

}
