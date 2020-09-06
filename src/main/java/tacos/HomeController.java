package tacos;

import tacos.web.WebConfig;

/**
 * 控制器类，用来处理主页相关的请求
 * 过时，使用 WebConfig 控制
 * @see WebConfig
 * @author licjd
 * @date 2020/09/05
 */
@Deprecated
//@Controller
public class HomeController {

//    @GetMapping("/")
    public String home() {
        return "home";
    }

}
