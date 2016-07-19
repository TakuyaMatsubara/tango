package jp.co.sbps;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	@Autowired
	private JdbcTemplate jdbc;
	
	@RequestMapping("/tango")
	public String tango(Model model){
		return "home";
	}
	
	
	@RequestMapping("/question")
	public String question(Model model){
		return "question";
	}
	
	@RequestMapping("/itil")
	public String itil(Model model){
		List<Map<String, Object>> list;
		list = jdbc.queryForList("select * from question ");
		model.addAttribute("question", list);
			
		
		Collections.shuffle(list);
		Map<String, Object> result=list.get(0);
		
		System.out.println(list);
		return "itil";
		
	}
	
	@RequestMapping(method=RequestMethod.POST ,path="/mark")
	public String RequestFormSubmit(MainForm mainForm, Model model){
		
		
		System.out.println("deug; "+mainForm);
		return "mark";
	}
		
	@RequestMapping("/questionlist")
	public String questionlist(Model model){
		return "questionlist";
		
	}
	
	@RequestMapping("/itillist")
	public String itillist(Model model){
		return "itillist";
	}
	
	@RequestMapping("/record")
	public String record(Model model){
		return "record";
	}
	
	@RequestMapping("/home")
	public String home(Model model){
		return "home";
	}
	
}


