package com.testsystem.MyTests.service;

import com.testsystem.MyTests.models.Answer;
import com.testsystem.MyTests.models.Question;
import com.testsystem.MyTests.models.Test;
import com.testsystem.MyTests.models.User;
import com.testsystem.MyTests.repository.AnswerRepository;
import com.testsystem.MyTests.repository.QuestionRepository;
import com.testsystem.MyTests.repository.TestRepository;
import com.testsystem.MyTests.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {

    @Autowired
    TestRepository testRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    public Long addNewTest(String username, String name, String description) {
        User u = userRepository.findByUsername(username);
        Test test = new Test(u, name, description);
        u.addTest(test);
        testRepository.save(test);
        userRepository.save(u);
        return test.getId();
    }

    public void addQuestion(Long idTest, String question, List<String> answers, Long trueAns) {
        Test test = testRepository.findById(idTest).get();
        Question quest = new Question();
        quest.setQuestion(question);
        quest.setTest(test);
        questionRepository.save(quest);
        List<Answer> answerList = new ArrayList<Answer>();
        for (int i = 0; i < answers.size(); i++) {
           if(i==trueAns) {
               Answer tempAns = new Answer(quest,answers.get(i),true);
               answerList.add(tempAns);
               answerRepository.save(tempAns);
           }
           else
            {
                Answer tempAns = new Answer(quest,answers.get(i),false);
                answerList.add(tempAns);
                answerRepository.save(tempAns);
            }
        }
        quest.setAnswer(answerList);
        test.addQuest(quest);
        questionRepository.save(quest);
        testRepository.save(test);

    }
    public void deleteQuest(Long id){
        Question quest = questionRepository.findById(id).get();
        if(quest!=null) questionRepository.deleteById(id);
    }
}
