package tacos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Order;
import tacos.Taco;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 构建配料的列表并传递给视图模板
 * @author licj
 * @date 2020/09/06
 */
@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

	private final IngredientRepository ingredientRepo;
	private TacoRepository designRepo;

	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository designRepo) {
		this.ingredientRepo = ingredientRepo;
		this.designRepo = designRepo;
	}

	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}

	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}

	@GetMapping
	public String showDesignForm(Model model) {
		List<Ingredient> ingredients = new ArrayList<>();
		ingredientRepo.findAll().forEach(i -> ingredients.add(i));

		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(),
					filterByType(ingredients, type));
		}
		
//		model.addAttribute("design", new Taco());
		return "design";
	}

	@PostMapping
	public String processDesign(
			@Valid Taco design, Errors errors,
			@ModelAttribute Order order) {
		if (errors.hasErrors()) {
			// 此处有两个异常：
			// 一是直接返回到页面，有提示信息，但是没有表单内容（动态生成的部分）；
			// 二是重定向到 redirect:/design 会缺少提示信息
			return "design";
		}

		Taco saved = designRepo.save(design);
		order.addDesign(saved);

		return "redirect:/orders/current";
	}

	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		return ingredients
				.stream()
				.filter(x -> x.getType().equals(type))
				.collect(Collectors.toList());
	}

}
