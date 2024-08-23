### 로깅
운영 시스템에서 System.out.println같은 시스템 콘솔을 사용해서 정보를 출력하지 않고 별도의 로깅 라이브러리를 사용해서 로그를 출력한다.</br>

SLF4J - http://www.slf4j.org</br>
Logback - http://logback.qos.ch</br>

### 로깅 라이브러리
LogBack, Log4J, Log4J2등 많은 라이브러리가 있는데, 이걸 통합해서 인터페이스로 제공하느 것이 SLF4J이다.


### 로그 선언
1. `private Logger log = LoggerFactory.getLogger(getClass());`
2. `private static final Logger log = LoggerFactory.getLogger(Xxx.class)`
3. `@Slf4j`

### 로그가 출력되는 포멧 확인
```
2024-08-23T16:34:12.437+09:00  INFO     26424 --- [nio-8080-exec-1] io.springmvc.basic.LogTestController     :  info log=Spring
시간                         로그Level 프로세스ID   쓰레드명           클래스명                                     로그 메세지
```

### 로그 레벨 설정 Properties
hello.springmvc 패키지와 그 하위 로그 레벨 설정
```
logging.level.io.springmvc=trace

logging.level.io.springmvc=debug

logging.level.io.springmvc=info
```

### 올바른 로그 사용
log.debug("data="+data)
    - 로그 출력 레벨을 info로 설정해도 해당 코드에 있는 "data="+data가 실제 실행이 되어 버린다. 결과적으로 문자 더하기 연산이 발생한다.
log.debug("data={}", data)
    - 로그 출력 레벨을 info로 설정하면 아무일도 발생하지 않는다. 따라서 앞과 같은 의미없는 연산이 발생하지 않는다.