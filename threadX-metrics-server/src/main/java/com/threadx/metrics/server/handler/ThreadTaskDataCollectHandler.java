package com.threadx.metrics.server.handler;

import cn.hutool.json.JSONUtil;
import com.threadx.communication.common.agreement.packet.ThreadPoolCollectMessage;
import com.threadx.communication.common.agreement.packet.ThreadPoolTaskCollectMessage;
import com.threadx.communication.common.handlers.ThreadXChannelInboundHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * 线程池中的任务数据处理器
 * 线程池任务相关的采集数据会发送到这个处理器中
 *
 * @author huangfukexing
 * @date 2023/4/7 13:37
 */
@Slf4j
@Component
public class ThreadTaskDataCollectHandler extends ThreadXChannelInboundHandler<ThreadPoolTaskCollectMessage> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ThreadPoolTaskCollectMessage taskCollectMessage) throws Exception {
        log.info("接收到任务数据: "+ JSONUtil.toJsonStr(taskCollectMessage));
    }
}
