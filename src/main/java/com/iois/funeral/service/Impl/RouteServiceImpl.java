package com.iois.funeral.service.Impl;

import com.alibaba.fastjson.JSON;
import com.iois.funeral.dao.UserDao;
import com.iois.funeral.entity.Dead;
import com.iois.funeral.entity.Person;
import com.iois.funeral.entity.User;
import com.iois.funeral.service.RouteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author: hesheng
 * @create: 2020-12-2020/12/30
 * @description:
 **/
@Service("routeService")
public class RouteServiceImpl implements RouteService {

    @Override
    public int updateDeadStage(User user) {
        stageHandler(user.getDead().getStage());
        return 0;
    }

    private void stageHandler(int stage){
        switch(stage){
            case 0:
                // stage 0: 未确定亲属关系，或无效的关联
                stage0();
                break;
            case 1:
                // stage 1: 确定亲属关系，无死亡证明=>前往开办死亡证明
                stage1();
                break;
            case 2:
                // stage 2： 已开具死亡证明，未火化=>殡仪馆选择
                stage2();
                break;
            case 3:
                // stage 3： 已火化，未下葬=>墓地选择
                stage3();
                break;
            case 4:
                // stage 4: 已下葬，财产未分割=>分割财产
                stage4();
                break;
            case 5:
                // stage 5: 财产分割完毕，所有流程结束
                stage5();
                break;
        }
    }


    private void stage0(){
        System.out.println("stage 0");
    }

    private void stage1(){
        System.out.println("stage 1");
    }

    private void stage2(){
        System.out.println("stage 2");
    }

    private void stage3(){
        System.out.println("stage 3");
    }

    private void stage4(){
        System.out.println("stage 4");
    }

    private void stage5(){
        System.out.println("stage 5");
    }
}
