package app.labs.dept.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import app.labs.dept.model.Dept;
import app.labs.dept.service.DeptService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("dept")
public class DeptController {
	@Autowired
	private DeptService deptService;
	
	@GetMapping(value = {"", "/", "main"})
	public String main(Model model) {
		model.addAttribute("serverTime", "서버시간");
		return "dept/main";
	}
	
	@GetMapping("count")
	public String getDeptCount(@RequestParam(value="deptid", required=false, defaultValue = "0")  int deptNo, Model model) {
		if(deptNo == 0) {
			int dept = deptService.getDeptCount();
			model.addAttribute("count", dept);
		} else {
			int dept = deptService.getDeptCount(deptNo);
			model.addAttribute("count", dept);			
		}
		return "dept/main";
	}
	
	@GetMapping("list")
	public String getAllDepts(Model model) {
		List<Dept> deptList = deptService.getDeptList();
		model.addAttribute("deptList", deptList);
		model.addAttribute("dept", new Dept());
		
		return "dept/list";
	}
	
	@PostMapping("insert")
	public String insertDept(Dept dept, RedirectAttributes redirectAttributes) {
		log.debug(dept.toString());
		
		try {
			deptService.insertDept(dept);
			redirectAttributes.addFlashAttribute("message", dept.getDeptNo() + "번 부서가 등록되었습니다.");
		} catch (RuntimeException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
		}

		return "redirect:/dept/list";
	}
	
	
	
}
