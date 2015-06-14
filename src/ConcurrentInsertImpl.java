import java.util.List;

/**
 * Created by Administrator on
 * User: zyljf012456
 * Date: 2015/6/7
 * Time: 16:33
 */
public class ConcurrentInsertImpl implements ConcurrentInsert {

    private static ConcurrentCache cache = new ConcurrentCacheImpl();

    @Override

    public Boolean concurrentInsert(String key, Object paramObj, String querySql, String insertSql) {
        if(cache.isExist(key))
            return false;
        if(!cache.put(key))
            return false;

        boolean flag = false;
        try{
            ConcurrentInsertDao dao = new ConcurrentInsertDaoImpl();
            List queryResult = dao.get(querySql, paramObj);
            System.out.println("query:" + paramObj + queryResult + ":" + System.currentTimeMillis());
            if(queryResult!=null && queryResult.size()>0){
                flag = true;
                return false;
            }

            dao.insert(insertSql, paramObj);
            flag = true;
        }finally {
            if(flag){
                cache.delete(key);
                System.out.println("delete cache:" + paramObj + ":" + System.currentTimeMillis());
            }
        }
        return true;
    }
}
