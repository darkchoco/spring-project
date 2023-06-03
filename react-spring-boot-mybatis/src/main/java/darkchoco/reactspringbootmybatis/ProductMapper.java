package darkchoco.reactspringbootmybatis;

import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;

@Mapper
public interface ProductMapper {

    @Insert("INSERT INTO product (product_name, description, price, filename) " +
            "VALUES (#{ product_name }, #{ description }, #{ price }, #{ filename })")
    int insert(String product_name, String description, BigDecimal price, String filename);

    @Select("SELECT * FROM product WHERE product_code = #{ product_code }")
    @Results(id="ProductMap")
    Product getByProductCode(@Param("product_code") Integer product_code);

    @Update("UPDATE product " +
            "SET product_name = #{ product_name }, " +
            "    description = #{ description },   " +
            "    price = #{ price },               " +
            "    filename = #{ filename }          " +
            "WHERE product_code = #{ product_code }")
    int update(Product product);

    @Delete("DELETE FROM product WHERE product_code = #{ product_code }")
    int delete(Integer product_code);
}
