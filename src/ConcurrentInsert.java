import java.util.Map;

/**
 * Created by Administrator on
 * User: zyljf012456
 * Date: 2015/6/7
 * Time: 11:06
 */
public interface ConcurrentInsert {
    /**
     * 并发插入处理类，同时多个线程对一条记录进行插入，在不使用数据库唯一键约束的情况下，控制数据库中只插入最早进行插入的一条记录。
     * @param key 插入对象的主键
     * @param paramObj 插入的对象
     * @param querySql 查询对象sql
     * @pazram insertSql 插入对象sql
     * @return Boolean:是否成功插入
     */
    Boolean concurrentInsert(String key, Object paramObj, String querySql, String insertSql);
}
