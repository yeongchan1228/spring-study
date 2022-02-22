package spring2.spring2Study.singleton;

public class SingletonService {

    //자바가 실행될 때 객체를 딱 1개만 생성한다.
    private static final SingletonService instance = new SingletonService(); // 싱글톤 패턴


    //public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용한다.
    public static SingletonService getInstance(){
        return instance;
    }

    private SingletonService(){ // private 생성자를 만들어서 외부로 new 방어
    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }

}
