import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    private static final String USERNAME = "Sebastian";
    private static final String PASSWORD = "Legende";

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session) {
        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            session.setAttribute("user", username);
            return "redirect:/quiz-app.html";
        } else {
            return "redirect:/login?error=true";
        }
    }
}
