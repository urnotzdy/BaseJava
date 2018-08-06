package redis;

import redis.clients.jedis.Jedis;

public class RedisTest {

    public static void main(String[] args) {
        Jedis jedis=new Jedis("127.0.0.1",8888);
//        jedis.set("name","zdy");
        System.out.println(jedis.get("name"));
    }

}
