package com.winterchen.common.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

import com.winterchen.common.utils.UserInfoUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import static com.winterchen.common.actor.SpringExtension.SpringExtProvider;


/**
 * ActorRef类的bean对象
 */
@Slf4j
public class ActorRefBean {

    private static ActorSystem actorSystem;

    private static Map<String, ActorRef> actorRefMap = new ConcurrentHashMap<>();

    public ActorRefBean(ActorSystem actorSystem){
        this.actorSystem = actorSystem;
    }
    /**
     * 发送Actor消息
     * @param beanName
     * @param msg
     */
    public void tell(String beanName, Object msg) {

        BaseActorMsg baseActorMsg = new BaseActorMsg();
        String userIdByCurrent = UserInfoUtils.getUserIdByCurrent();
        baseActorMsg.setUserId(userIdByCurrent);
        baseActorMsg.setObject(msg);

        ActorRef actorRef = actorRefMap.get(beanName);
        if(Objects.isNull(actorRef)){
            actorRef = initActorRef(beanName);
        }
        actorRef.tell(baseActorMsg, ActorRef.noSender());

    }


    /**
     * 发送Actor消息
     * @param tClass 对于的消费者class
     * @param msg
     */
    public void tell(Class tClass, Object msg) {

        BaseActorMsg baseActorMsg = new BaseActorMsg();
        String userIdByCurrent = UserInfoUtils.getUserIdByCurrent();
        baseActorMsg.setUserId(userIdByCurrent);
        baseActorMsg.setObject(msg);
        String beanName = StringUtils.uncapitalize(tClass.getSimpleName());
        Component annotation = AnnotationUtils.findAnnotation(tClass, Component.class);
        if (annotation != null && StringUtils.isNotEmpty(annotation.value())) {
            beanName = annotation.value();
        }
        ActorRef actorRef = actorRefMap.get(beanName);
        if(Objects.isNull(actorRef)){
            actorRef = initActorRef(beanName);
        }
        if (actorRef == null) {
            log.error("actorRef 为空，异步处理akka失败");

            return;
        }
        actorRef.tell(baseActorMsg, ActorRef.noSender());

    }

    private synchronized ActorRef initActorRef(String beanName){
        // double check，确保actor不会被重复初始化
        ActorRef actorRef = actorRefMap.get(beanName);
        if(Objects.isNull(actorRef)){
            try {
                actorRef = actorSystem.actorOf(SpringExtProvider.get(actorSystem).props(beanName), beanName);
                actorRefMap.putIfAbsent(beanName, actorRef);
            } catch (Exception e) {
                System.err.println("111");

            }

        }
        return actorRef;
    }


}
