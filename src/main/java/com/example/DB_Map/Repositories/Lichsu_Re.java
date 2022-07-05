package com.example.DB_Map.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.core.CrudMethods;

import com.example.DB_Map.Model.Lichsu;

public interface Lichsu_Re extends CrudRepository<Lichsu, Integer>{
	@Query(value = "SELECT * FROM lichsu WHERE lichsu.ls_tk_id=?1",nativeQuery = true)
	Iterable<Lichsu> timTaikhoan(Integer id);
	
}
