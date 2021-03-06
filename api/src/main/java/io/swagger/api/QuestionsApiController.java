package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.model.Question;
import io.swagger.repository.QuestionRepository;
import io.swagger.repository.SemesterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-25T16:55:34.601Z")

@Controller
public class QuestionsApiController implements QuestionsApi {

    private static final Logger log = LoggerFactory.getLogger(QuestionsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    SemesterRepository semesterRepository;

    @org.springframework.beans.factory.annotation.Autowired
    public QuestionsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> activateQuestion(@ApiParam(value = "",required=true) @PathVariable("questionId") Long questionId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            Question questionToActivate = questionRepository.findOne(questionId);
            if (questionToActivate == null) {
                return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
            }
            questionToActivate.setIsActive(true);
            questionRepository.save(questionToActivate);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Void> createQuestion(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Question body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            questionRepository.save(body);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        }
        return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Void> deactivateQuestion(@ApiParam(value = "",required=true) @PathVariable("questionId") Long questionId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            Question questionToDeactivate = questionRepository.findOne(questionId);
            if (questionToDeactivate == null) {
                return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
            }
            questionToDeactivate.setIsActive(false);
            questionRepository.save(questionToDeactivate);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getActiveQuestions(@ApiParam(value = "",required=true) @PathVariable("evalType") String evalType) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            List<Question> activeQuestionList = new ArrayList<Question>();
            Iterator<Question> questionIterator = questionRepository.findQuestionsByIsActiveAndEvalTypeOrderByQuestionNumber(true, Question.EvalType.valueOf(evalType)).iterator();
            while(questionIterator.hasNext())
            {
                activeQuestionList.add(questionIterator.next());
            }
            return new ResponseEntity<List<Question>>(activeQuestionList, HttpStatus.OK);
        }
        return new ResponseEntity<List<Question>>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Question> getQuestion(@ApiParam(value = "",required=true) @PathVariable("questionId") Long questionId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            Question question = questionRepository.findOne(questionId);
            if (question == null) {
                return new ResponseEntity<Question>(HttpStatus.NOT_FOUND);
            }
            else {
                return new ResponseEntity<Question>(question, HttpStatus.OK);
            }
        }
        return new ResponseEntity<Question>(HttpStatus.BAD_REQUEST);
    }

    @Transactional
    public ResponseEntity<Void> updateQuestions(@ApiParam(value = "" ,required=true )  @Valid @RequestBody List<Question> body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            for (Question question: body) {
                // retrieve the old question using the questionId
                Question oldQuestion = questionRepository.findOne(question.getQuestionId());
                if (oldQuestion == null) {
                    return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
                }
                else {
                    // if the question has been modified
                    if (oldQuestion.equals(question) == false) {
                        // mark the old question as inactive so it can still be used for completed evals
                        oldQuestion.setIsActive(false);
                        questionRepository.save(oldQuestion);

                        // remove the questionId from the new question so that a new question is stored
                        question.setQuestionId(null);
                        questionRepository.save(question);
                    }
                }
            }

            return new ResponseEntity<Void>(HttpStatus.OK);




        }
        return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }

}
