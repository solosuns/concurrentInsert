import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

/**
 * Created by Administrator on
 * User: zyljf012456
 * Date: 2015/6/9
 * Time: 6:55
 */
public class ConcurrentCacheTest{
    private ConcurrentCache cache;

    @Before
    public void init(){
        cache = new ConcurrentCacheImpl();
    }

    @Test
    public void testIsTimeOut(){
        Calendar inTime = Calendar.getInstance();
        inTime.add(Calendar.SECOND, -11);
        System.out.print(cache.isTimeOut(inTime));
    }

    @Test
    public void testIsExist(){
        String key = "test0";
        System.out.println(cache.isExist(key));

        System.out.println(cache.put(key));
        System.out.println(cache.isExist(key));

        System.out.println(cache.put(key));

        System.out.println(cache.put(key="test1"));
    }

}
