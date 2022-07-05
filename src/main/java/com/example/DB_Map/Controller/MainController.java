package com.example.DB_Map.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.DB_Map.Model.Diadiemdulich;
import com.example.DB_Map.Model.Lichsu;
import com.example.DB_Map.Model.Mien;
import com.example.DB_Map.Model.Vitri;
import com.example.DB_Map.Repositories.Diadiemdulich_Re;
import com.example.DB_Map.Repositories.Lichsu_Re;
import com.example.DB_Map.Repositories.Mien_Re;
import com.example.DB_Map.Repositories.Vitri_Re;


@Controller
public class MainController {
	@Autowired 
	Diadiemdulich_Re diadiemdulich_Re;
	@Autowired 
	Vitri_Re vitri_Re;
	@Autowired
	Mien_Re mien_Re;
	@Autowired
	Lichsu_Re lichsu_Re;
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public ModelAndView diadiemdulich(ModelMap modelMap,@ModelAttribute("Diadiemdulich") Diadiemdulich Diadiemdulich,  HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("admin")==null) {
			return new ModelAndView("backend/login-admin");
		}
		modelMap.addAttribute("Diadiemdulich", diadiemdulich_Re.findAll());
		return new ModelAndView("backend/diadiemdulich");
	}

	// insert du lieu 
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert(ModelMap modelMap) {
		Iterable<Vitri> Vitri = vitri_Re.selectBy();
        modelMap.addAttribute("Vitri", Vitri);
        modelMap.addAttribute("Mien",mien_Re.findAll());
		modelMap.addAttribute("Diadiemdulich",new Diadiemdulich());
		return("backend/insert");
	}
	
	 @RequestMapping(value = "/insert", method = RequestMethod.POST)
	 public String insert(ModelMap modelMap, @Validated @ModelAttribute("Diadiemdulich") Diadiemdulich Diadiemdulich, BindingResult bindingResult,@RequestParam(value = "file", required = false) MultipartFile imgFile) {
		 if (bindingResult.hasErrors()) {
				modelMap.addAttribute("Vitri", vitri_Re.selectBy());
		        modelMap.addAttribute("Mien",mien_Re.findAll());
			return "backend/insert";
		}
		 
		 if(imgFile.isEmpty()==false) {
				String path = "src/main/webapp/resources/pictures/";
				String url = "/resources/pictures/";
				
				try {
					String fileName = imgFile.getOriginalFilename() ;
					String filePath = path + "/";
					Diadiemdulich.setDddl_path(url+fileName);
					FileUploadUtil.saveFile(filePath, fileName, imgFile);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
		 }
		 

		diadiemdulich_Re.save(Diadiemdulich);
		return "redirect:/home";
	 }
	 
	// update du lieu
		@RequestMapping(value="/home/view/{dddl_id}", method=RequestMethod.GET)
		public String update(ModelMap modelMap, @PathVariable Integer dddl_id) {
			//modelMap.addAttribute("Diadiemdulich",diadiemdulich_Re.findAll());
			modelMap.addAttribute("Diadiemdulich",diadiemdulich_Re.findById(dddl_id).get());
			modelMap.addAttribute("Mien",mien_Re.findAll());
			return("backend/update");
		}
		
		@RequestMapping(value="/home/update/{dddl_id}", method=RequestMethod.POST)
		public String update1(ModelMap modelMap, @Validated @ModelAttribute("Diadiemdulich") Diadiemdulich Diadiemdulich,BindingResult bindingResult ,@PathVariable Integer dddl_id,@RequestParam(value = "file", required = false) MultipartFile imgFile) {
			Iterable<Mien> Mien= mien_Re.findAll(); 
			
			 if(imgFile.isEmpty()==false) {
					String path = "src/main/webapp/resources/pictures/";
					String url = "/resources/pictures/";
					
					try {
						String fileName = imgFile.getOriginalFilename() ;
						String filePath = path + "/";
						Diadiemdulich.setDddl_path(url+fileName);
						FileUploadUtil.saveFile(filePath, fileName, imgFile);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
			 }
			 
			if (bindingResult.hasErrors()) {
				 	modelMap.addAttribute("Mien",Mien);
					return "backend/update";	
				}
			try {
				 if (diadiemdulich_Re.findById(dddl_id).isPresent()) {
						Diadiemdulich founddiadiemdulich = diadiemdulich_Re.findById(dddl_id).get();
						if (!Diadiemdulich.getDddl_ten().trim().isEmpty()) {
							founddiadiemdulich.setDddl_ten(Diadiemdulich.getDddl_ten());
						}
						if (!Diadiemdulich.getDddl_mota().trim().isEmpty()) {
							founddiadiemdulich.setDddl_mota(Diadiemdulich.getDddl_mota());
						}
						founddiadiemdulich.setDddl_path(Diadiemdulich.getDddl_path());
						founddiadiemdulich.setMien(Diadiemdulich.getMien());	
						diadiemdulich_Re.save(founddiadiemdulich);
					}
			} catch (Exception e) {
				return "backend/update";	
			}			
			return "redirect:/home/";
		}
	 
	 // xoa du lieu
	 @RequestMapping(value = "/delete/{dddl_id}", method = RequestMethod.POST)
	 public String deleteProduct(ModelMap modelMap, @PathVariable Integer dddl_id) {
	    diadiemdulich_Re.deleteById(dddl_id);
	    return "redirect:/home";
	 }
	 
		@RequestMapping(value = "/logout1", method = RequestMethod.GET)
		public ModelAndView logout(HttpServletRequest request) {
			HttpSession session = request.getSession();
			System.out.println("remove session " + session.getAttribute("name"));
			session.removeAttribute("name");
			return new ModelAndView("/frontend/login");
		}

//////	 // Lay kinh do, vi do
//////		@RequestMapping(value="/",method = RequestMethod.GET)
//////		public String getkinhdovido(ModelMap modelMap,@ModelAttribute("KDVD") Vitri KDVD) {
//////			System.out.print("fufuyfy"+KDVD.getVitri_id());
//////			modelMap.addAttribute("KDVD",vitri_Re.findById(KDVD.getVitri_id()).get());
//////			return("/");
//////		}
////	 //---------------------------------------------Goi frontend-----------------------------------------
		@RequestMapping(value="/", method=RequestMethod.GET)
		public ModelAndView index(ModelMap modelMap, HttpServletRequest request) {
			HttpSession session = request.getSession();
			if(session.getAttribute("name")==null) {
				return new ModelAndView("/frontend/login");
			}
			Iterable<Diadiemdulich> Diadiemdulich = diadiemdulich_Re.findMien(1);
			modelMap.addAttribute("Diadiemdulich",Diadiemdulich);
			modelMap.addAttribute("Lichsu",new Lichsu());
			Iterable<Lichsu> lichsu= lichsu_Re.timTaikhoan((Integer) session.getAttribute("userid"));
			System.out.print((Integer) session.getAttribute("userid"));
			modelMap.addAttribute("Lichsu1",lichsu);
			return new ModelAndView("index");
		}
//	@RequestMapping(value="/", method=RequestMethod.GET)
//		public String index1(ModelMap modelMap,@CookieValue(value = "name", defaultValue = "none") String cookie, HttpServletRequest request) {
//			HttpSession session = request.getSession();
//			if(session.getAttribute("name")==null) {
//				return ("/frontend/login");
//			}
//			Iterable<Diadiemdulich> Diadiemdulich = diadiemdulich_Re.findMien(1);
//			modelMap.addAttribute("Diadiemdulich",Diadiemdulich);
//			modelMap.addAttribute("Lichsu",new Lichsu());
//			Iterable<Lichsu> lichsu= lichsu_Re.findAll();
//			modelMap.addAttribute("Lichsu1",lichsu);
//			return "/";
//		}
		
		// chuyen trang sang mien bac
		@RequestMapping(value="/mienbac", method=RequestMethod.GET)
		public ModelAndView chuyenmienbac(ModelMap modelMap, @CookieValue(value = "name", defaultValue = "none") String cookie, HttpServletRequest request) {
			HttpSession session = request.getSession();
			if(session.getAttribute("name")==null) {
				return new ModelAndView("/frontend/login");
			}
			Iterable<Diadiemdulich> Diadiemdulich = diadiemdulich_Re.findMien(3);
			modelMap.addAttribute("Diadiemdulich",Diadiemdulich);
			modelMap.addAttribute("Lichsu",new Lichsu());
			Iterable<Lichsu> lichsu= lichsu_Re.timTaikhoan((Integer) session.getAttribute("userid"));
			modelMap.addAttribute("Lichsu1",lichsu);
			return new ModelAndView("/frontend/mienbac");
		}
		
		
		// chuyen trang sang mien trung
		@RequestMapping(value="/mientrung", method=RequestMethod.GET)
		public ModelAndView chuyenmientrung(ModelMap modelMap, @CookieValue(value = "name", defaultValue = "none") String cookie, HttpServletRequest request) {
			HttpSession session = request.getSession();
			if(session.getAttribute("name")==null) {
				return new ModelAndView("/frontend/login");
			}
			Iterable<Diadiemdulich> Diadiemdulich = diadiemdulich_Re.findMien(2);
			modelMap.addAttribute("Diadiemdulich",Diadiemdulich);
			modelMap.addAttribute("Lichsu",new Lichsu());
			Iterable<Lichsu> lichsu= lichsu_Re.timTaikhoan((Integer) session.getAttribute("userid"));
			modelMap.addAttribute("Lichsu1",lichsu);
			return new ModelAndView("/frontend/mientrung");
		}
		
//		
//
////		@GetMapping("/login")
////		public ModelAndView login() {
////			return new ModelAndView("/frontend/login");
////		}
//		
////		@GetMapping("/")
////		public ModelAndView index() {
////			return new ModelAndView("index");
////		}
			
}
