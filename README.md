# laboratory-jdk

My tiny little laboratory for testing JDK, JSP, Java third party libraries and ETC.

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
