import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on
 * User: zyljf012456
 * Date: 2015/6/7
 * Time: 16:32
 */
public class ConcurrentCacheImpl implements ConcurrentCache {
//    private ConcurrentHashMap<String, Calendar> cacheMap = new ConcurrentHashMap<>();
    private Map<String, Calendar> cacheMap = new HashMap<>();
    private static int TIMEOUT = 60000;
    @Override
    public Boolean isExist(String key) {
        Calendar time;
        if((time=cacheMap.get(key))==null)
            return false;
        if(isTimeOut(time)){
            cacheMap.remove(key);
            return false;
        }
        return true;
    }

    @Override
    public synchronized Boolean put(String key) {
        if(isExist(key))
            return false;
        cacheMap.put(key, Calendar.getInstance());
        return true;
    }

    @Override
    public Boolean isTimeOut(Calendar time) {
        if(time == null)
            return false;
        return (Calendar.getInstance().getTimeInMillis()-(time.getTimeInMillis()+TIMEOUT))>=0;
    }

    @Override
    public Boolean delete(String key) {
        return cacheMap.remove(key)!=null;
    }


}
