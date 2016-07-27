package jp.co.sbps;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TangoApplication.class)
public class MainControllerTest {

	@Autowired
	MainController controller;
	
	@Test
	public void testrequestFormSubmit() {
		List<Map<String,Object>> questions = controller.findQuestionById(Arrays.asList(1, 2, 3, 5));
		boolean correctAnswer = (boolean)questions.get(0).get("correct_answer");
		
		for (Map<String, Object> question : questions) {
			System.out.println(question.get("question_id") + " : " + question.get("correct_answer"));
		}
		assertThat(questions.size(), is(4));
		
		System.out.println(correctAnswer);
		System.out.println((boolean)questions.get(0).get("correct_answer"));
	}

}
