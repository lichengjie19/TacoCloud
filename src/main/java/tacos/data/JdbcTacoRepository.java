package tacos.data;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import tacos.Ingredient;
import tacos.Taco;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

/**
 * 保存 taco 和 ingredient 的关联关系
 * @author licj
 * @date 2020/09/06
 */
@Repository
public class JdbcTacoRepository implements TacoRepository {

  private JdbcTemplate jdbc;

  public JdbcTacoRepository(JdbcTemplate jdbc) {
    this.jdbc = jdbc;
  }

  @Override
  public Taco save(Taco taco) {
    long tacoId = saveTacoInfo(taco);
    taco.setId(tacoId);
    for (Ingredient ingredient : taco.getIngredients()) {
      saveIngredientToTaco(ingredient, tacoId);
    }

    return taco;
  }

  private long saveTacoInfo(Taco taco) {
    taco.setCreatedAt(new Date());
    PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory("insert into Taco (name, createdAt) values (?, ?)", Types.VARCHAR,Types.TIMESTAMP);
    PreparedStatementCreator creator = factory.newPreparedStatementCreator(Arrays.asList(taco.getName(),new Timestamp(taco.getCreatedAt().getTime())));

    KeyHolder keyHolder = new GeneratedKeyHolder();
    factory.setReturnGeneratedKeys(true); //允许返回主键值
    jdbc.update(creator, keyHolder);
    // 2020/09/06 无法获取 id
    // 2020/09/13 通过此片文章解决：https://blog.csdn.net/AbstractLiu/article/details/105545776
    return keyHolder.getKey().longValue();
  }

  private void saveIngredientToTaco(
          Ingredient ingredient, long tacoId) {
    jdbc.update(
        "insert into Taco_Ingredients (taco, ingredient) " +
        "values (?, ?)",
        tacoId, ingredient.getId());
  }

}
