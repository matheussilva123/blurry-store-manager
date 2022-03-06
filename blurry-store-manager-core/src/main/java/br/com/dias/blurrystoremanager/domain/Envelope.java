package br.com.dias.blurrystoremanager.domain;

import br.com.dias.blurrystoremanager.enums.EventType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Envelope {
    private EventType eventType;
    private String data;
}
