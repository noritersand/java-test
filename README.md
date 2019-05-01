# laboratory

my little laboratory.

#### environments

- maven
- jdk10
- tomcat9

## TODO

- 파폭에서 'http://localhost:8080/test/uncategorized/read-body.data' 요청 안날라가는거 확인할 것
- uncategorized 밑에 있는 얘들 분류할 것.
- 파일명 변경. (바로 아래 도움말을 봐라)

## 글 작성 도움말

파일명은 경로나 폴더, 패키지 참고 없이 파일만으로 성격을 파악할 수 있을 수준으로 작성한다. e.g. `/webapp/test/js/js-date-test.html`

2019-04-18 기준 스타일(css)이 가장 잘 정의된 파일은 `browser-history-test.html`이다.

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