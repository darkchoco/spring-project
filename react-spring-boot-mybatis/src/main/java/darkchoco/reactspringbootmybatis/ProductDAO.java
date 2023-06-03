package darkchoco.reactspringbootmybatis;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class ProductDAO {

    private final SqlSession sqlSession;

    public List<Map<String, Object>> list(String product_name) {
        return sqlSession.selectList("product.list", "%" + product_name + "%");
    }

//    public void insert(Map<String, Object> map) {
//        sqlSession.insert("product.insert", map);
//    }

    public String filename(int product_code) {
        return sqlSession.selectOne("product.filename", product_code);
    }
}
