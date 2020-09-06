package tacos;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

/**
 * @WebMvcTest(HomeController.class)
 * Spring Boot 提供的一个特殊测试注解，会让这个测试在 Spring MVC 应用的上下文中执行。
 * @author licj
 * @date 2020/09/06
 */
@RunWith(SpringRunner.class)
@WebMvcTest(HomeController.class)   // <1>
public class HomeControllerTest {

  @Autowired
  private MockMvc mockMvc;   // <2>

  @Test
  public void testHomePage() throws Exception {
    mockMvc.perform(get("/"))    // <3> 发起对"/"的 GET
      .andExpect(status().isOk())  // <4> 期望得到 HTTP 200
      .andExpect(view().name("home"))  // <5> 期望得到的视图
      .andExpect(content().string(containsString("Welcome to...")))  // <6> 期望包含"Welcome to..."
      .andDo(MockMvcResultHandlers.print());
  }

}
