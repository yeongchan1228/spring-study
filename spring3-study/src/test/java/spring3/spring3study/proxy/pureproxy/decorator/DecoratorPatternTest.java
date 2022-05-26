package spring3.spring3study.proxy.pureproxy.decorator;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import spring3.spring3study.proxy.pureproxy.decorator.code.DecoratorPatternClient;
import spring3.spring3study.proxy.pureproxy.decorator.code.MessageDecorator;
import spring3.spring3study.proxy.pureproxy.decorator.code.RealComponent;
import spring3.spring3study.proxy.pureproxy.decorator.code.TimeDecorator;

@Slf4j
public class DecoratorPatternTest {

    @Test
    void noDecorator(){
        DecoratorPatternClient client = new DecoratorPatternClient(new RealComponent());
        client.execute();
        client.execute();
        client.execute();
    }

    @Test
    void messageDecorator(){
        DecoratorPatternClient client = new DecoratorPatternClient(new MessageDecorator(new RealComponent()));
        client.execute();
    }

    @Test
    void timeDecorator(){
        DecoratorPatternClient client = new DecoratorPatternClient(new TimeDecorator(new MessageDecorator(new RealComponent())));
        client.execute();
    }
}
