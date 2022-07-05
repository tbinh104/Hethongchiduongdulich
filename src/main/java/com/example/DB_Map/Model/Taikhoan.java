package com.example.DB_Map.Model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "taikhoan")
public class Taikhoan {
	@Id
	@Column(name = "tk_id")
	int tkid;
	@Column(name = "tk_tendangnhap")
	String tktendangnhap;
	@Column(name = "tk_matkhau")
	String tkmatkhau;
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "taikhoan")
	private Set<Lichsu> lichsu;
	public Taikhoan() {
		super();
	}
	public Set<Lichsu> getLichsu() {
		return lichsu;
	}
	public void setLichsu(Set<Lichsu> lichsu) {
		this.lichsu = lichsu;
	}
	public Taikhoan(int tkid, String tktendangnhap, String tkmatkhau, Set<Lichsu> lichsu) {
		super();
		this.tkid = tkid;
		this.tktendangnhap = tktendangnhap;
		this.tkmatkhau = tkmatkhau;
		this.lichsu = lichsu;
	}
	public Taikhoan(int tkid, String tktendangnhap, String tkmatkhau) {
		super();
		this.tkid = tkid;
		this.tktendangnhap = tktendangnhap;
		this.tkmatkhau = tkmatkhau;
	}
	public Taikhoan(String tktendangnhap, String tkmatkhau) {
		super();
		this.tktendangnhap = tktendangnhap;
		this.tkmatkhau = tkmatkhau;
	}
	public Taikhoan(int tkid) {
		super();
		this.tkid=tkid;
	}
	public int getTkid() {
		return tkid;
	}
	public void setTkid(int tkid) {
		this.tkid = tkid;
	}
	public String getTktendangnhap() {
		return tktendangnhap;
	}
	public void setTktendangnhap(String tktendangnhap) {
		this.tktendangnhap = tktendangnhap;
	}
	public String getTkmatkhau() {
		return tkmatkhau;
	}
	public void setTkmatkhau(String tkmatkhau) {
		this.tkmatkhau = tkmatkhau;
	}
	
	
	
}
