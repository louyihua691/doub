package com.xxx.doub.remoting.transport.netty;

import com.xxx.doub.remoting.exchange.protobuf.RequestProto;
import com.xxx.doub.remoting.transport.AbstractClient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * Created by louyihua on 2016/8/4 13:53.
 */
public class NettyClient extends AbstractClient
{
    private Bootstrap bootstrap;

    private volatile Channel channel;

    protected void doOpen()
    {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(workerGroup).channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY,true)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,getConnectTimeOut())
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast(new ChunkedWriteHandler());
                        p.addLast(new ProtobufEncoder());
                        p.addLast(new ProtobufDecoder(RequestProto.Request.getDefaultInstance()));
                        p.addLast(new NettyClientHandler());
                    }
                });
    }

    protected void doConnect()
    {

    }
}
