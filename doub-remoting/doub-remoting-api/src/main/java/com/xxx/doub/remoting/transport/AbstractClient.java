package com.xxx.doub.remoting.transport;

import com.xxx.doub.remoting.Client;

/**
 * Created by louyihua on 2016/8/4 13:54.
 */
public abstract class AbstractClient implements Client
{
    private int connectTimeOut = 1000;

    public  AbstractClient()
    {

    }

    protected abstract void doOpen();

    protected abstract void  doConnect();

    protected int getConnectTimeOut() {
        return connectTimeOut;
    }
}
