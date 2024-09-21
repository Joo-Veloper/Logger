package io.springmvc.typeconverter.converter;

import io.springmvc.typeconverter.type.IpPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
@Slf4j
public class IpPortToStringConvert implements Converter<IpPort, String> {

    @Override
    public String convert(IpPort source) {
        log.info("convert source={}", source);

        // IpPort객체 -> "127.0.0:8080"
        return source.getIp() + ":" + source.getPort();
    }
}
