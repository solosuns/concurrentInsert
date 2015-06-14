import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on
 * User: zyljf012456
 * Date: 2015/6/11
 * Time: 7:22
 */
public class ConcurrentInsertDaoTest {

    private ConcurrentInsertDao concurrentInsertDao;

    @Before
    public void init(){
        concurrentInsertDao = new ConcurrentInsertDaoImpl();
    }

    @Test
    public void testInsert(){
        Map paramMap = new HashMap();
        paramMap.put("id","testDao2");
        paramMap.put("name","testDaon");
        System.out.println(concurrentInsertDao.insert("concurrencInsert", paramMap));
    }

    @Test
    public void testQuery(){
        Map paramMap = new HashMap();
        paramMap.put("id","testDao1");
        System.out.println(concurrentInsertDao.get("concurrentQuery", paramMap));
    }

    @Test
    public void testDelete(){
        Map paramMap = new HashMap();
        paramMap.put("id","test1");
        System.out.println(concurrentInsertDao.delete("deleteTestData", paramMap));
    }
}
