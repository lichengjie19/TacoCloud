package tacos.data;

import tacos.Ingredient;

/**
 * 配料库
 * @author licj
 * @date 2020/09/06
 */
public interface IngredientRepository {

  /**
   * 查询所有的配料信息，将它们放在一个 Ingredient 对象的集合中
   * @return 所有的配料信息
   */
  Iterable<Ingredient> findAll();

  /**
   * 根据 id，查询单个 Ingredient
   * @param id 唯一主键
   * @return 配料信息
   */
  Ingredient findById(String id);

  /**
   * 保存 Ingredient
   * @param ingredient 待保存的配料信息
   * @return 保存后的配料信息
   */
  Ingredient save(Ingredient ingredient);
  
}
