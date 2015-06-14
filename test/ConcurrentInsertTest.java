import org.junit.Before;
import org.junit.Test;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on
 * User: zyljf012456
 * Date: 2015/6/11
 * Time: 6:49
 */
public class ConcurrentInsertTest {

    private ConcurrentInsert concurrentInsert;
    private ConcurrentInsertDao concurrentDao;

    @Before
    public void init(){
        concurrentInsert = new ConcurrentInsertImpl();
        concurrentDao = new ConcurrentInsertDaoImpl();
    }

    private String querySql = "concurrentQuery";
    private String insertSql = "concurrencInsert";
    private String deleteSql = "deleteTestData";

    @Test
    public void testConcurrentInsert() throws InterruptedException {
        String key ="test1";
        Map paramObj = new HashMap();
        paramObj.put("id",key);
        paramObj.put("name",key+2);

        System.out.println("test0:" + concurrentInsert.concurrentInsert(key, paramObj, querySql, insertSql));
        System.out.println("test0:" + concurrentInsert.concurrentInsert(key, paramObj, querySql, insertSql));
        Thread.sleep(1000);
        System.out.println("test0:" + concurrentInsert.concurrentInsert(key, paramObj, querySql, insertSql));
    }

    /**
     * 1.通过循环获取线程集合
     * 2.同时执行所有线程
     */
    @Test
    public void testConcurrentInsertMutiThreads() throws InterruptedException {
        int threadNum = 10000;
        String name = "jy";
        List<InsertThread> threadList = new ArrayList<>();
        ExecutorService pool = Executors.newFixedThreadPool(10000);
        for(int i=0; i<threadNum; i++){
            InsertThread it = new InsertThread(name+i, concurrentInsert);
            threadList.add(it);
        }
        Calendar begin = Calendar.getInstance();
        pool.invokeAll(threadList);
        Calendar end = Calendar.getInstance();

        Map tmp = new HashMap();
        tmp.put("id","muti");
        System.out.println(concurrentDao.get(querySql,tmp));
        System.out.println(concurrentDao.delete(deleteSql, tmp));
        System.out.println(end.getTimeInMillis() - begin.getTimeInMillis());
    }

    class InsertThread implements Callable<Integer> {
        private Map paramMap;
        private ConcurrentInsert concurrentInsert;
        private String key;
        public InsertThread(String name, ConcurrentInsert concurrentInsert){
            key = "muti";
            paramMap = new HashMap();
            paramMap.put("id", key);
            paramMap.put("name", name);
            this.concurrentInsert = concurrentInsert;
        }

        @Override
        public Integer call() throws Exception {
//            System.out.println("in"+paramMap);
            boolean result = concurrentInsert.concurrentInsert(key, paramMap, "concurrentQuery", "concurrencInsert");
//            System.out.println("out"+paramMap+result);

            return 1;
        }
    }
}
