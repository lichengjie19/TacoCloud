package tacos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 控制器类，用来处理主页相关的请求
 * @author licjd
 * @date 2020/09/05
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

}
