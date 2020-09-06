package tacos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tacos.Order;

import javax.validation.Valid;

/**
 * 展现 taco 订单表单的控制器
 * @author licj
 * @date 2020/09/06
 */
@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {
  
  @GetMapping("/current")
  public String orderForm(Model model) {
    model.addAttribute("order", new Order());
    return "orderForm";
  }

  @PostMapping
  public String processOrder(@Valid Order order, Errors errors) {
    if (errors.hasErrors()) {
      return "orderForm";
    }

    log.info("Order submitted: " + order);
    return "redirect:/";
  }

}
