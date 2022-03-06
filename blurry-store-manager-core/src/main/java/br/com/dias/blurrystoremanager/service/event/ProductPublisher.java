package br.com.dias.blurrystoremanager.service.event;

import br.com.dias.blurrystoremanager.domain.Product;
import br.com.dias.blurrystoremanager.enums.EventType;

public interface ProductPublisher {

    void publishProductEvent(Product product, EventType eventType);

}
