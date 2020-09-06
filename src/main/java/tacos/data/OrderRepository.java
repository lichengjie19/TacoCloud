package tacos.data;

import tacos.Order;

/**
 * 订单库
 * @author licj
 * @date 2020/09/06
 */
public interface OrderRepository {

  /**
   * 保存订单
   * @param order 订单
   * @return 保存好的订单
   */
  Order save(Order order);
  
}
