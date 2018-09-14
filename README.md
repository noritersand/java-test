# laboratory
my little laboratory

#### environments
- maven
- jdk10
- tomcat9

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