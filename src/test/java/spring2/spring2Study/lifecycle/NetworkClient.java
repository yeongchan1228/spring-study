package spring2.spring2Study.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


public class NetworkClient /*implements InitializingBean, DisposableBean  -> 스프링에 의존적이다. 초창기에 나온 방법*/ {
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }


    //서비스 시작 시 호출
    public void connect(){
        System.out.println("connect: "+url);
    }


    public void call(String message){
        System.out.println("call : "+url + " message : "+message);
    }

    //서비스 종료 시 호출
    public void disconnect(){
        System.out.println("close : "+url);
    }

    /*@Override
    public void afterPropertiesSet() throws Exception { // 의존 관계 주입이 다 끝난 후에 실행한다.
        connect();
        call("초기화 연결 메시지");
    }

    @Override
    public void destroy() throws Exception { // 빈이 종료되기 전에 호출된다.
        disconnect();
    }*/


    /*public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }


    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }*/

    @PostConstruct // 의존관계 설정 완료 후 실행
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy // 빈이 파괴되기 전 실행
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
