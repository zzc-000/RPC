package com.zzc.client.core;

import com.zzc.client.param.ClientRequest;
import com.zzc.client.param.Response;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DefaultFuture {

    public final static  ConcurrentHashMap<Long,DefaultFuture>allDefaultFuture=new ConcurrentHashMap<Long,DefaultFuture>();
    final Lock lock = new ReentrantLock();
    public Condition condition=lock.newCondition();
    private Response response;
    private long timeout=2*60*1000;
    private long startTime = System.currentTimeMillis();
    public DefaultFuture(ClientRequest request){
        allDefaultFuture.put(request.getId(),this);

    }

    public long getTimeout() {
        return timeout;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    //主线程获取数据
    public Response get(){
        lock.lock();
        try{
            while(!done()){
                condition.await();
            }

        }catch(Exception e){

        }finally {
            lock.unlock();
        }
        return this.response;
    }
    public Response get(long time){
        lock.lock();
        try{
            while(!done()){
                condition.await(time, TimeUnit.SECONDS);
                if((System.currentTimeMillis()-startTime)>time) {
                    System.out.println("请求超时");
                    break;
                }
            }

        }catch(Exception e){

        }finally {
            lock.unlock();
        }
        return this.response;
    }
    public static void receive(Response response){
        DefaultFuture df = allDefaultFuture.get(response.getId());
        if(df!=null){
            Lock lock = df.lock;
            lock.lock();
            try{
                df.setResponse(response);
                df.condition.signal();
            }catch(Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

        }
    }
    private boolean done() {
        if(this.response!=null){
            return true;
        }
        return false;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    static class FutureThread extends Thread{
        @Override
        public void run() {
            Set<Long> ids = allDefaultFuture.keySet();
            for(Long id:ids){
                DefaultFuture df = allDefaultFuture.get(id);
                if(df==null){
                    allDefaultFuture.remove(df);
                }else{
                    if(df.getTimeout()<(System.currentTimeMillis()-df.getStartTime())){
                        Response resp = new Response();
                        resp.setId(id);
                        resp.setCode("333333");
                        resp.setMsg("链路请求超时");
                        receive(resp);
                    }
                }
            }
        }
    }
    static{
        FutureThread futureThread = new FutureThread();
        futureThread.setDaemon(true);//后台守护线程
        futureThread.start();
    }
}
















