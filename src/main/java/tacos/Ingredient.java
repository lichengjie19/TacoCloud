package tacos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * taco 配料 - 领域类
 * @author licj
 * @date 2020/09/06
 */
@Data
@RequiredArgsConstructor
public class Ingredient {

  /**
   * 唯一主键
   */
  private final String id;

  /**
   * 名称
   */
  private final String name;

  /**
   * 配料类型
   */
  private final Type type;
  
  public static enum Type {
    // 用…包裹, 蛋白质, 蔬菜, 奶酪, 酱汁
    WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
  }

}
