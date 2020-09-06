package tacos.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tacos.Ingredient;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * IngredientRepository 实现
 * @author licj
 * @date 2020/09/06
 */
@Repository
public class JdbcIngredientRepository implements IngredientRepository {

  private JdbcTemplate jdbc;
  
  @Autowired
  public JdbcIngredientRepository(JdbcTemplate jdbc) {
    this.jdbc = jdbc;
  }

  /**
   * 查询所有的配料信息，将它们放在一个 Ingredient 对象的集合中
   * @return 所有的配料信息
   */
  @Override
  public Iterable<Ingredient> findAll() {
    return jdbc.query("select id, name, type from Ingredient",
        this::mapRowToIngredient);
  }

  /**
   * 根据 id，查询单个 Ingredient
   * @param id 唯一主键
   * @return 配料信息
   */
  @Override
  public Ingredient findById(String id) {
    return jdbc.queryForObject(
        "select id, name, type from Ingredient where id=?",
        this::mapRowToIngredient, id);
  }
  
  /*
  上面 findById 简化
  @Override
  public Ingredient findOne(String id) {
    return jdbc.queryForObject(
        "select id, name, type from Ingredient where id=?",
        new RowMapper<Ingredient>() {
          public Ingredient mapRow(ResultSet rs, int rowNum) 
              throws SQLException {
            return new Ingredient(
                rs.getString("id"), 
                rs.getString("name"),
                Ingredient.Type.valueOf(rs.getString("type")));
          };
        }, id);
  }
   */

  /**
   * 保存 Ingredient
   * @param ingredient 待保存的配料信息
   * @return 保存后的配料信息
   */
  @Override
  public Ingredient save(Ingredient ingredient) {
    jdbc.update(
        "insert into Ingredient (id, name, type) values (?, ?, ?)",
        ingredient.getId(), 
        ingredient.getName(),
        ingredient.getType().toString());
    return ingredient;
  }

  private Ingredient mapRowToIngredient(ResultSet rs, int rowNum)
      throws SQLException {
    return new Ingredient(
        rs.getString("id"), 
        rs.getString("name"),
        Ingredient.Type.valueOf(rs.getString("type")));
  }

}
