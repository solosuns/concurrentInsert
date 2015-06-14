import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on
 * User: zyljf012456
 * Date: 2015/6/7
 * Time: 16:33
 */
public class ConcurrentInsertDaoImpl implements ConcurrentInsertDao {
    static SqlSessionFactory sqlSessionFactory = null;
    static {
        sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
    }
    @Override
    public List get(String querySql, Object paramObj) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            return sqlSession.selectList(querySql, paramObj);
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public int insert(String insertSql, Object paramObj) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        int n;
        try {
//            ConcurrentUserMapper userMapper = sqlSession.getMapper(ConcurrentUserMapper.class);
//            userMapper.concurrencInsert((Map) paramObj);
            n = sqlSession.insert(insertSql, paramObj);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
        return n;
    }

    @Override
    public int delete(String deleteSql, Object paramObj) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        int n;
        try {
            n = sqlSession.delete(deleteSql, paramObj);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
        return n;
    }
}
