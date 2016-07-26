package jp.co.sbps;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.qos.logback.core.net.SyslogOutputStream;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TangoApplication.class)
public class MainControllerTest {

	@Autowired
	MainController controller;
	
	@Test
	public void testRequestFormSubmit() {
		List<Map<String,Object>> questions = controller.findQuestionById(Arrays.asList(1, 2, 3, 4));
		boolean correctAnswer = (boolean)questions.get(0).get("correct_answer");
		
		for (Map<String, Object> question : questions) {
			System.out.println(question.get("question_id") + " : " + question.get("correct_answer"));
		}
		assertThat(questions.size(), is(4));
		
		System.out.println(correctAnswer);
		System.out.println((boolean)questions.get(0).get("correct_answer"));
	}

}