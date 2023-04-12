package com.threadx.metrics.server.handler;

import cn.hutool.json.JSONUtil;
import com.threadx.communication.common.agreement.packet.ThreadPoolCollectMessage;
import com.threadx.communication.common.handlers.ThreadXChannelInboundHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * 线程池数据处理器
 * 线程池相关的采集数据会发送到这个处理器中
 *
 * @author huangfukexing
 * @date 2023/4/7 13:37
 */
@Slf4j
@Component
public class ThreadPoolDataCollectHandler extends ThreadXChannelInboundHandler<ThreadPoolCollectMessage> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ThreadPoolCollectMessage threadPoolCollectMessage) throws Exception {
        log.info("接收到数据: "+ JSONUtil.toJsonStr(threadPoolCollectMessage));
    }
}
