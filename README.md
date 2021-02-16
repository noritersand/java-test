# laboratory-java

Java와 써드 파티 라이브러리 테스트용 저장소. 

사용 방법:

- 메이븐이나 JUnit으로 자바 테스트.
- 이클립스 내에서 WST로 띄우거나, WAR로 배포 후 톰캣 등의 WAS로 기동해서 JSP 테스트.
- WAS 설정에서 **context path는 빈 값`/`으로** 할 것.

#### environments

- maven
- ojdkbuild12
- tomcat9

## 작성 규칙

- 스프링은 스프링 테스트 프로젝트로.
- 자바 클래스를 제외한 파일명은 소문자로만 작성할 것.
- 파일명은 경로나 폴더, 패키지 참고 없이 파일만으로 성격을 파악할 수 있을 수준으로 작성한다. e.g. `/webapp/page/js/js-date-test.html`

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
