package com.threadx.communication.client;

import com.threadx.communication.client.config.ClientConfig;
import com.threadx.communication.common.MessageCommunicationConfig;
import com.threadx.communication.common.agreement.AgreementChoreography;
import com.threadx.communication.common.agreement.implementation.PacketSegmentationHandler;
import com.threadx.communication.common.agreement.packet.Message;
import com.threadx.communication.common.handlers.PacketCodecHandler;
import com.threadx.communication.common.utils.ChannelUtil;
import com.threadx.communication.common.utils.NettyEventLoopUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;

import java.net.InetSocketAddress;
import java.util.logging.Logger;

/**
 * 客户端
 *
 * @author huangfukexing
 * @date 2023/4/7 14:31
 */
public class CommunicationClient {

    static final Logger logger = Logger.getGlobal();

    private static final int DEFAULT_CONNECT_TIMEOUT = 3000;

    private final static int DEFAULT_IO_THREADS = Math.min(Runtime.getRuntime().availableProcessors() + 1, 32);

    private static final EventLoopGroup EVENT_LOOP_GROUP = NettyEventLoopUtils.eventLoopGroup(DEFAULT_IO_THREADS, "threadX-Client-Worker");

    /**
     * 启动器
     */
    private Bootstrap bootstrap;

    /**
     * 通讯管道
     */
    private Channel channel;

    /**
     * 连接的服务端的地址
     */
    private String serverAddress;

    /**
     * 配置信息
     */
    private final ClientConfig clientConfig;

    public CommunicationClient(ClientConfig clientConfig) {
        this.clientConfig = clientConfig;
        //连接服务器
        connect();
    }

    /**
     * 重新连接
     *
     * @throws Throwable 异常信息
     */
    public void reConnect() throws Throwable {
        this.close();
        connect();
    }

    /**
     * 连接服务器
     */
    private void connect() {
        bootstrap = new Bootstrap();
        bootstrap.group(EVENT_LOOP_GROUP)
                //设置通道选项 SO_KEEPALIVE 为 true，表示启用 TCP 的 keepalive 机制，即使长时间没有数据传输也能保持连接。
                .option(ChannelOption.SO_KEEPALIVE, Boolean.TRUE)
                //设置通道选项 TCP_NODELAY 为 true，表示禁用 Nagle 算法，可以降低延迟但会增加网络负载。
                .option(ChannelOption.TCP_NODELAY, Boolean.TRUE)
                //设置通道选项 ALLOCATOR 为 Netty 提供的对象池化的字节缓冲区分配器，可以减少内存分配和垃圾回收的开销，提高性能。
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                //设置通道类型为 NettyEventLoopUtils 工具类提供的套接字通道类型，该类型会根据操作系统的不同选择合适的实现。
                .channel(NettyEventLoopUtils.socketChannelClass());
        bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, DEFAULT_CONNECT_TIMEOUT);

        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                //获取消息协议上下文
                MessageCommunicationConfig communicationConfig = clientConfig.getMessageCommunicationConfig();

                //获取网络数据包分割器
                AgreementChoreography agreementChoreography = communicationConfig.getAgreementChoreography();
                PacketSegmentationHandler packetSegmentationHandler = agreementChoreography.segmentationHandler();

                //数据通讯管道编排
                //写入数据包分割器  能区分一个完整的包数据
                socketChannel.pipeline().addLast("PacketSegmentationHandler", packetSegmentationHandler);
                //写入数据编解码器  将一个完整的包数据进行包编解码
                socketChannel.pipeline().addLast("PacketCodecHandler", new PacketCodecHandler(communicationConfig));
                //todo 来自服务端的命令处理器
            }
        });

        //开始连接服务器
        ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress(clientConfig.getHost(), clientConfig.getPort())).syncUninterruptibly();
        channel = channelFuture.channel();
        //获取连接的地址
        serverAddress = ChannelUtil.getChannelRemoteAddress(channel);
        logger.info("连接服务端成功：" + serverAddress);
    }


    /**
     * 异步的发送一个消息
     *
     * @param message 消息体
     */
    public void asyncSendMessage(Message message) {
        message.setServerKey(clientConfig.getServerKey());
        message.setInstanceKey(clientConfig.getInstanceKey());
        channel.writeAndFlush(message);
    }


    /**
     * 等到服务端关闭
     *
     * @throws InterruptedException 中断异常
     */
    public void await() throws InterruptedException {
        channel.closeFuture().sync();
    }

    /**
     * 关闭服务端
     *
     * @throws Throwable 异常信息
     */
    public void close() throws Throwable {
        if (bootstrap != null) {
            EVENT_LOOP_GROUP.shutdownGracefully().syncUninterruptibly();
        }
    }

    /**
     * 判断通道是否活跃
     *
     * @return 是否活跃
     */
    public boolean communicationStatus() {
        return channel.isOpen() && channel.isActive();
    }

    public String getServerAddress() {
        return serverAddress;
    }
}
