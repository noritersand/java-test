# java-lab

Java와 써드 파티 라이브러리 테스트용 저장소. 

사용 방법:

- 메이븐이나 JUnit으로 자바 클래스 유닛 테스트
- 톰캣 등의 WAS로 기동해서 JSP 테스트
- WAS 설정에서 **context path는 빈 값`/`으로** 할 것.

#### environments

- maven
- Java 11

## 작성 규칙

- 스프링은 스프링 테스트 프로젝트로

## SSL 설정하기

콘솔에서 키 생성 (JDK를 이용한다):

```bash
keytool -genkey -alias tomcat -keyalg RSA
```

server.xml에 다음 항목 추가:

```xml
<Connector SSLEnabled="true" clientAuth="false"
		keystorefile="${userprofile}\.keystore" keystorePass="123456"
		maxThreads="150" port="8443" scheme="https" secure="true" sslProtocol="TLS" />
```

https://localhost:8443 접속 테스트

끗
