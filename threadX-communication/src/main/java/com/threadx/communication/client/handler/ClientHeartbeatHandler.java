package com.threadx.communication.client.handler;

import com.threadx.communication.client.CommunicationClient;
import com.threadx.communication.common.agreement.packet.HeartbeatMessage;
import com.threadx.communication.common.handlers.ThreadXChannelInboundHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.ScheduledFuture;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 客户端心跳处理器
 *
 * @author huangfukexing
 * @date 2023/4/25 13:13
 */
public class ClientHeartbeatHandler extends ThreadXChannelInboundHandler<HeartbeatMessage> {

    /**
     * 心跳间隔
     */
    public static final int HEARTBEAT_INTERVAL = 2;

    /**
     * 心跳超时时间
     */
    public static final int HEARTBEAT_TIMEOUT = 10;


    private final AtomicLong RECEIVE_TIME = new AtomicLong(System.currentTimeMillis());

    private ScheduledFuture<?> schedule;

    private final CommunicationClient communicationClient;

    public ClientHeartbeatHandler(CommunicationClient communicationClient) {
        this.communicationClient = communicationClient;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //开启增加心跳定时
        schedule = ctx.executor().schedule(() -> {
            long receiveTime = RECEIVE_TIME.get();
            if ((System.currentTimeMillis() - receiveTime) > TimeUnit.SECONDS.toMillis(HEARTBEAT_TIMEOUT)) {
                communicationClient.failureThisConnection();
            }
            HeartbeatMessage heartbeatMessage = new HeartbeatMessage();
            communicationClient.asyncSendMessage(heartbeatMessage);
        }, HEARTBEAT_INTERVAL, TimeUnit.SECONDS);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartbeatMessage msg) throws Exception {
        RECEIVE_TIME.set(System.currentTimeMillis());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        schedule.cancel(true);
        super.channelInactive(ctx);
    }
}
