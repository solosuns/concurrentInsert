import java.util.Calendar;

/**
 * Created by Administrator on
 * User: zyljf012456
 * Date: 2015/6/7
 * Time: 16:32
 */
public interface ConcurrentCache {
    /**
     *
     * @param key
     * @return
     */
    Boolean isExist(String key);

    /**
     *
     * @param key
     * @return
     */
    Boolean put(String key);

    /**
     *
     * @param time
     * @return
     */
    Boolean isTimeOut(Calendar time);

    /**
     *
     * @param key
     * @return
     */
    Boolean delete(String key);
}
