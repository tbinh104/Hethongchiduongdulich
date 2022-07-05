package com.example.DB_Map.Controller;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.naming.java.javaURLContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.DB_Map.Model.Diadiemdulich;
import com.example.DB_Map.Model.Lichsu;
import com.example.DB_Map.Model.Taikhoan;
import com.example.DB_Map.Model.Vitri;
import com.example.DB_Map.Repositories.Diadiemdulich_Re;
import com.example.DB_Map.Repositories.Lichsu_Re;
import com.example.DB_Map.Repositories.Taikhoan_Re;

@Controller
public class LichsuController {
	@Autowired
	Lichsu_Re lichsu_Re;
	@Autowired
	Diadiemdulich_Re diadiemdulich_Re;
	@Autowired
	Taikhoan_Re taikhoan_Re;
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String lichsu(ModelMap modelMap) {
//		Iterable<Lichsu> lichsu = lichsu_Re.findAll();
//		modelMap.addAttribute("Lichsu",lichsu);
//		return "frontend/lichsu";	
//	}
	
//	// insert du lieu 
//		@RequestMapping(value="/", method=RequestMethod.GET)
//		public String insert(ModelMap modelMap) {
//	        modelMap.addAttribute("Diadiemdulich",diadiemdulich_Re.findAll());
//	        modelMap.addAttribute("Taikhoan",taikhoan_Re.findById(1));
//			modelMap.addAttribute("Lichsu",new Lichsu());
//			return("/");
//		}
		@RequestMapping(value = "/", method = RequestMethod.POST)
		 public void insert(ModelMap modelMap,@ModelAttribute("Lichsu") Lichsu Lichsu,@RequestParam String dddlid, @RequestParam String dddlten, @RequestParam String dddlmota,@RequestParam String tk, HttpServletRequest request) {
				HttpSession session = request.getSession();
				Date date=java.util.Calendar.getInstance().getTime(); 
				Diadiemdulich diadiemdulich = new Diadiemdulich(Integer.valueOf(dddlid),dddlten,dddlmota);
				Taikhoan taikhoan = new Taikhoan(Integer.valueOf(tk));
				Lichsu lichsu = new Lichsu(date.toString(),diadiemdulich,taikhoan );
				lichsu_Re.save(lichsu);		
		 }
		
		@RequestMapping(value = "/mienbac", method = RequestMethod.POST)
		 public void insertmb(ModelMap modelMap, @ModelAttribute("Lichsu") Lichsu Lichsu,@RequestParam String dddlid, @RequestParam String dddlten, @RequestParam String dddlmota,@RequestParam String tk, HttpServletRequest request) {
				Date date=java.util.Calendar.getInstance().getTime(); 
				Diadiemdulich diadiemdulich = new Diadiemdulich(Integer.valueOf(dddlid),dddlten,dddlmota);
				Taikhoan taikhoan = new Taikhoan(Integer.valueOf(tk));
				Lichsu lichsu = new Lichsu(date.toString(),diadiemdulich,taikhoan );
				lichsu_Re.save(lichsu);		
		 }
		
		@RequestMapping(value = "/mientrung", method = RequestMethod.POST)
		 public void insertmt(ModelMap modelMap,@ModelAttribute("Lichsu") Lichsu Lichsu, @RequestParam String dddlid, @RequestParam String dddlten, @RequestParam String dddlmota,@RequestParam String tk, HttpServletRequest request) {
				Date date=java.util.Calendar.getInstance().getTime(); 
				Diadiemdulich diadiemdulich = new Diadiemdulich(Integer.valueOf(dddlid),dddlten,dddlmota);
				Taikhoan taikhoan = new Taikhoan(Integer.valueOf(tk));
				Lichsu lichsu = new Lichsu(date.toString(),diadiemdulich,taikhoan );
				lichsu_Re.save(lichsu);		
		 }
		// trang lich su
		@RequestMapping(value = "/lichsu", method = RequestMethod.GET)
		 public ModelAndView insertmb(ModelMap modelMap, HttpServletRequest request) {
			HttpSession session = request.getSession();
			Iterable<Lichsu> lichsu= lichsu_Re.timTaikhoan((Integer) session.getAttribute("userid"));
			modelMap.addAttribute("Lichsu",lichsu);	
			modelMap.addAttribute("Lichsu1",new Lichsu());
			return new ModelAndView("frontend/lichsu");
	 }
		
		@RequestMapping(value = "/lichsu", method = RequestMethod.POST)
		 public void lichsu(ModelMap modelMap,@ModelAttribute("Lichsu") Lichsu Lichsu, @RequestParam String dddlid, @RequestParam String dddlten, @RequestParam String dddlmota,@RequestParam String tk, HttpServletRequest request) {
				Date date=java.util.Calendar.getInstance().getTime(); 
				Diadiemdulich diadiemdulich = new Diadiemdulich(Integer.valueOf(dddlid),dddlten,dddlmota);
				Taikhoan taikhoan = new Taikhoan(Integer.valueOf(tk));
				Lichsu lichsu = new Lichsu(date.toString(),diadiemdulich,taikhoan );
				lichsu_Re.save(lichsu);		
		 }

}
