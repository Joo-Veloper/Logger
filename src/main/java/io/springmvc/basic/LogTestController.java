package io.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
/**
 * RestController
 * Spring Framework에서 제공하는 애너테이션으로, RESTful 웹 서비스를 구현할 때 사용됩니다.
 * 애너테이션을 사용하면 Spring이 해당 클래스를 REST 컨트롤러로 인식하고,
 * HTTP 요청을 처리하는 메서드를 정의할 수 있게 됩니다
 */
@RestController
public class LogTestController {
    /*private final Logger log = LoggerFactory.getLogger(getClass());*/

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name);

        /** log Level*/

        /** trace
         * 가장 상세한 로그 레벨로, 애플리케이션의 실행 흐름과 디버깅 정보를 상세히 기록한다. 주로 디버깅 시에 사용된다
         */
        log.trace("trace log={}", name);

        // + 사용 X -> 문자 더하기로 인해 연산이 발생하기 때문에 쓸모 없는 리소스를 사용하게 됩니다.
        log.trace("trace log=" + name);

        /** debug
         * 디버깅 목적으로 사용되며, 개발 단계에서 상세한 정보를 기록한다.
         * 애플리케이션의 내부 동작을 이해하고 문제를 분석하는 데 도움을 준다.
         */
        log.debug("debug log={}", name);

        /** info
         * 정보성 메시지를 기록한다.
         * 애플리케이션의 주요 이벤트나 실행 상태에 대한 정보를 전달한다.
         */
        log.info(" info log={}", name);

        /** warn
         * 경고성 메시지를 기록한다.
         * 예상치 못한 문제나 잠재적인 오류 상황을 알리는 메시지이다.
         * 애플리케이션이 정상적으로 동작하지만 주의가 필요한 상황을 알려준다.
         */
        log.warn(" warn log={}", name);

        /** error
         * 오류 메시지를 기록한다.
         * 심각한 문제 또는 예외 상황을 나타내며, 애플리케이션의 정상적인 동작에 영향을 미칠 수 있는 문제를 알린다.
         */
        log.error("error log={}", name);

        return "ok";
    }
}
