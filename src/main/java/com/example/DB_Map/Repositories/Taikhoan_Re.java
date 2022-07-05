package com.example.DB_Map.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.DB_Map.Model.Taikhoan;

public interface Taikhoan_Re extends CrudRepository<Taikhoan, Integer>{	
		Taikhoan findByTktendangnhap(String userName);
		Taikhoan findByTktendangnhapAndTkmatkhau(String userName, String passWord);
}
