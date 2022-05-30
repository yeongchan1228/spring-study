package spring3.spring3study.proxy.jdkdynamic.code;

public interface BInterface {
    String call();

    default String call2() {
        return "default Method";
    }
}
