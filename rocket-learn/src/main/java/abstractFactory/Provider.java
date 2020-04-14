package abstractFactory;


import abstractFactory.staticFactory.Sender;

public interface Provider {
    Sender produce();
}
