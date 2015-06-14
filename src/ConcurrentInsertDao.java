import java.util.List;

/**
 * Created by Administrator on
 * User: zyljf012456
 * Date: 2015/6/7
 * Time: 16:33
 */
public interface ConcurrentInsertDao {
    /**
     *
     * @param querySql
     * @param paramObj
     * @return
     */
    List get(String querySql, Object paramObj);

    /**
     *
     * @param insertSql
     * @param paramObj
     * @return
     */
    int insert(String insertSql, Object paramObj);

    int delete(String deleteSql, Object paramObj);
}
