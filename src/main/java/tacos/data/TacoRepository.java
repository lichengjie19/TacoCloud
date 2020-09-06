package tacos.data;

import tacos.Taco;

/**
 * taco 库
 * @author licj
 * @date 2020/09/06
 */
public interface TacoRepository  {

  /**
   * 保存 taco
   * @param design taco
   * @return taco
   */
  Taco save(Taco design);
  
}
