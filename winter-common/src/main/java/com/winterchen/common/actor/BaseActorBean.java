package com.winterchen.common.actor;

import akka.actor.UntypedActor;

/**
 * Spring中ActorBean的基类
 */
public abstract class BaseActorBean<T> extends UntypedActor {


    @Override
    public void onReceive(Object message){
        BaseActorMsg<T> baseActorMsg = (BaseActorMsg) message;
        onReceiveMsg(baseActorMsg.getObject());
    }

    public abstract void onReceiveMsg(T message);

}
