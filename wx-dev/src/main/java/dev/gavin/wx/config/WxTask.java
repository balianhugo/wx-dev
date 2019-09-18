package dev.gavin.wx.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@EnableScheduling
public class WxTask {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private WxConfig config;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Scheduled(fixedRate = 7000*1000)
    public void taskOne(){
        updateWxAccessToken(config.getAppID(), config.getAppsecret());
        String wxAccessToken = stringRedisTemplate.opsForValue().get("wxAccessToken");
        updateJsaepiTicket(wxAccessToken);
    }

    private String formatNowTime() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(new Date());
    }

    private void updateWxAccessToken(String appID, String appSecret){
        String wxAccessToken;
        long tryTime = 6*1000; //休眠间隔时间
        int times = 6; //失败后最大请求次数
        do {
            wxAccessToken = WxTools.getWxAccessToken(appID, appSecret);
            if(wxAccessToken.isEmpty()) {
                // 取值失败则增加一倍时间再请求一次，若 6 次失败则不再请求，避免线程不回收内存溢出，记录到错误日志中
                try {
                    Thread.sleep(tryTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                times--;
                if(times != 0) {
                    tryTime *=2;
                }else {
                    logger.error("updateWxAccessToken failure! appKey: {}, appSecret: {}", appID, appSecret);
                    break;
                }

            } else {
                logger.info("wxAccessToken : {}", wxAccessToken);
                stringRedisTemplate.opsForValue().set("wxAccessToken", wxAccessToken);
                stringRedisTemplate.opsForValue().set("wxAccessTokenTime", formatNowTime());
            }
        } while (wxAccessToken.isEmpty());

    }

    private void updateJsaepiTicket(String wxAccessToken){
        String wxJsaepiTicket;
        long tryTime = 6*1000; //休眠间隔时间
        int times = 6; //失败后最大请求次数
        do {
            wxJsaepiTicket = WxTools.getJsapiTicket(wxAccessToken);
            if(wxJsaepiTicket.isEmpty()) {
                // 取值失败则增加一倍时间再请求一次，若 6 次失败则不再请求，避免线程不回收内存溢出，记录到错误日志中
                try {
                    Thread.sleep(tryTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                times--;
                if(times != 0) {
                    tryTime *=2;
                }else {
                    logger.error("updatJsapiTicket failure! wxAccessToken: {}", wxAccessToken);
                    break;
                }

            } else {
                logger.info("wxJsaepiTicket : {}", wxJsaepiTicket);
                stringRedisTemplate.opsForValue().set("wxJsaepiTicket", wxJsaepiTicket);
                stringRedisTemplate.opsForValue().set("wxJsaepiTicketTime", formatNowTime());
            }
        } while (wxJsaepiTicket.isEmpty());

    }

}
