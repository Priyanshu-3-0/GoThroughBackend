package Controllers;


import entity.Feedback;
import repositories.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    @Autowired private FeedbackRepository feedbackRepository;

    @PostMapping
    public Feedback give(@RequestBody Feedback feedback){ return feedbackRepository.save(feedback); }

    @GetMapping
    public List<Feedback> all(){ return feedbackRepository.findAll(); }
}
