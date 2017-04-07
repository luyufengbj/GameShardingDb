package com.snowcattle.game.db.service.test.redis;

import com.snowcattle.game.db.cache.redis.RedisService;

/**
 * Created by jiangwenping on 17/4/7.
 */
public class RedisPop extends Thread{
    RedisService redisService;
    private String setKey;
    private String listKey;

    public RedisPop(RedisService redisService, String setKey, String listKey) {
        this.redisService = redisService;
        this.setKey = setKey;
        this.listKey = listKey;
    }

    @Override
    public void run() {
        while (true){
            String key = redisService.spopString(setKey);
            if(key != null) {
                while (true) {
                    String popKey = redisService.lpop(key);
                    if(popKey != null){
                        System.out.println("弹出"+ popKey);
                    }else{
                        break;
                    }
                }
            }
        }
    }
}