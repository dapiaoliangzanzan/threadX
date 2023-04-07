package com.threadx.communication.server.handler;

import cn.hutool.json.JSONUtil;
import com.threadx.communication.common.agreement.packet.ThreadPoolCollectMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.logging.Logger;

/**
 * 线程池数据处理器
 * 线程池相关的采集数据会发送到这个处理器中
 *
 * @author huangfukexing
 * @date 2023/4/7 13:37
 */
@ChannelHandler.Sharable
public class ThreadPoolDataCollectHandler extends SimpleChannelInboundHandler<ThreadPoolCollectMessage> {

    static final Logger logger = Logger.getGlobal();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ThreadPoolCollectMessage threadPoolCollectMessage) throws Exception {
        logger.info("接收到数据: "+ JSONUtil.toJsonStr(threadPoolCollectMessage));
    }
}
