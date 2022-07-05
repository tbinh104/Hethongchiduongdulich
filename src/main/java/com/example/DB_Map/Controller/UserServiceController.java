package com.example.DB_Map.Controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.DB_Map.Model.Taikhoan;
import com.example.DB_Map.Repositories.Taikhoan_Re;

@Controller
public class UserServiceController {
	
	@Autowired
	Taikhoan_Re taiKhoan_RE;
	@Autowired
	HttpSession session ;
	@Autowired
	HttpSession  session1 ;
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView signup(@RequestParam String name, @RequestParam String password, HttpServletResponse response, Model model) {
		Taikhoan result = taiKhoan_RE.findByTktendangnhap(name);
		if (result == null) {
			result = new Taikhoan();
			result.setTktendangnhap(name);
			result.setTkmatkhau(password);
			taiKhoan_RE.save(result);
			String message = "Tạo tài khoản thành công !";
			model.addAttribute("message", message);
			return new ModelAndView ("/frontend/login");
		}
		else {
			String message = "Tài khoản đã tồn tại !";
			model.addAttribute("message", message);
			return new ModelAndView ("/frontend/signup");
		}
		
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam String name, @RequestParam String password, HttpServletResponse response, Model model, HttpServletRequest request) {
		Taikhoan taikhoan = new Taikhoan(name, password);
		session=request.getSession();
		System.out.println(session.toString());
		if (taiKhoan_RE.findByTktendangnhapAndTkmatkhau(name, password) == null) {
			String message = "Sai ten tai khoan hoac mat khau";
			model.addAttribute("message", message);
			return new ModelAndView("/frontend/login");
		}
		if (taiKhoan_RE.findByTktendangnhapAndTkmatkhau(name, password)!=null&&taiKhoan_RE.findByTktendangnhap(name)==taiKhoan_RE.findByTktendangnhap("admin")) {
			return new ModelAndView("/backend/login-admin");
		}
		
		else {
			session.setAttribute("name", name);
			Taikhoan taikhoan2 =new Taikhoan();
			taikhoan2=taiKhoan_RE.findByTktendangnhapAndTkmatkhau(name, password);
			session.setAttribute("userid", taikhoan2.getTkid());
			return new ModelAndView("redirect:/");
		}
	
	}
	// login admin
	@RequestMapping(value = "/loginadmin", method = RequestMethod.POST)
	public ModelAndView loginadmin(@RequestParam String admin, @RequestParam String password, HttpServletResponse response, Model model, HttpServletRequest request) {
		Taikhoan taikhoan = new Taikhoan(admin, password);
		session1 =request.getSession();
		if (taiKhoan_RE.findByTktendangnhapAndTkmatkhau(admin, password) == null) {
			String message = "Sai ten tai khoan hoac mat khau";
			model.addAttribute("message", message);
			return new ModelAndView("/backend/login-admin");
		}
		if (taiKhoan_RE.findByTktendangnhapAndTkmatkhau(admin, password)!=null&&taiKhoan_RE.findByTktendangnhap(admin)==taiKhoan_RE.findByTktendangnhap("admin")) {
			session1.setAttribute("admin", admin);
			Taikhoan taikhoan2 =new Taikhoan();
			taikhoan2=taiKhoan_RE.findByTktendangnhapAndTkmatkhau(admin, password);
			session1.setAttribute("userid", taikhoan2.getTkid());
			return new ModelAndView("redirect:/home");
		}
			
		return new ModelAndView("/backend/login-admin");
	
		 
	}
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(@RequestParam String name, Model model) {
		String test = "";
		model.addAttribute("test", test);
		return "/frontend/test";
	}

	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public String ptest(@RequestParam String name, Model model) {
		String test = name;
		System.out.println("name: " + name);
		model.addAttribute("test", test);
		return "/frontend/test";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		System.out.println("remove session " + session.getAttribute("name"));
		session.removeAttribute("name");
		return new ModelAndView("/frontend/login");
	}
	@RequestMapping(value = "/logoutadmin", method = RequestMethod.GET)
	public ModelAndView logout1(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("admin");
		return new ModelAndView("/backend/login-admin");
	}

	@GetMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("/frontend/login");
	}
	
	@GetMapping("/loginadmin")
	public ModelAndView loginadmin() {
		return new ModelAndView("/backend/login-admin");
	}

	@GetMapping("/signup")
	public ModelAndView signup() {
		return new ModelAndView("/frontend/signup");
	}
	
}