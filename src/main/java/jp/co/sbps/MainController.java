// 更新DAZE

package jp.co.sbps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	@Autowired
	private NamedParameterJdbcTemplate namedParamJdbc;
	
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
		
		System.out.println(result);
		return "itil";
	}
	
	@RequestMapping(method=RequestMethod.POST ,path="/mark")
	public String RequestFormSubmit(MainForm mainForm, Model model){
		
		List<Map<String, Object>> questions = findQuestionById(createIds(mainForm.getRequests()));
		List<Map<String, Object>> homeanswer = new ArrayList<Map<String, Object>>();
		//入力list文を繰り返し
		for (RequestForm requestForm : mainForm.getRequests()) {
			//出力用のmapを作る・DBのlistから同じidを探す
			
			Map<String, Object> findid	=findQuestionById(requestForm.getQuestionId(), questions);
			findid.put("answer", requestForm.getAnswer());
			findid.put("question_name", requestForm.getQuestionName());
			homeanswer.add(findid);
			
			
			boolean maru =true ;
			if(maru==true){
				 System.out.println("○");
				}else {
					System.out.println("×");
				}
				
		}
	
		
		//出力用のmapを作る
		model.addAttribute("requests", homeanswer);
		
		
		
		return "mark";
	}
		private Map<String, Object> findQuestionById(String id,List<Map<String, Object>> questions) {
			
			for (Map<String, Object> question : questions) {
				if(id.equals(question.get("question_id").toString())){
					return question;
				}
					
			}
			
				
			return null;
		}
	
		
		
		
	@RequestMapping("/questionlist")
	public String questionlist(Model model){
		return "questionlist";
		
	}
	
	@RequestMapping("/additillist")
	public String additillist(@RequestParam String komoku,String situmon, String kaitou,Model model){
		model.addAttribute("komoku",komoku);
		System.out.println(komoku);
		/*jdbc.update(
				"insert into _table (id,komoku,situmon,kaitou) values(:koumoku,:situmon,:kaitou)");*/ 
		return "itillist";
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
	
	
	List<Integer> createIds(List<RequestForm> forms) {
		ArrayList<Integer> ids = new ArrayList<>();
		for (RequestForm request : forms) {
			ids.add(Integer.parseInt(request.getQuestionId()));
		}
		return ids;
	}
	
	/**
	 * IDをもとにQuestionsを取得する
	 * 
	 * @param forms
	 * @return Questions
	 */
	List<Map<String, Object>> findQuestionById(List<Integer> ids) {
		HashMap<String, List<Integer>> map = new HashMap<>();
		map.put("requests",ids);
		return namedParamJdbc.queryForList("select question_id , correct_answer from question  where question_id in(:requests)", map);
		
	} 
	
	
}
