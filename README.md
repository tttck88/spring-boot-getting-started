# 의존성 관리

스프링부트**의 주요 기능 중 하나인 의존성 관리에 대해 학습해보자**

스프링부트의 주요 기능이자 장점 중 하나는 바로 라이브러리에 대한 관리를 자동을 해준다는 것인데

어떤 원리인지 알아보자면 다음과 같다

`spring-boot-starter-parent` → `spring-boot-dependencie`

![스크린샷 2022-02-06 오후 7.16.54.png](https://user-images.githubusercontent.com/39619488/152677117-c2fc7bef-90b0-4de2-8166-f8c2984c094b.png)

![스크린샷 2022-02-06 오후 7.18.00.png](https://user-images.githubusercontent.com/39619488/152677167-63674d9f-c8e5-4063-97e5-4fcc84994ff4.png)

![스크린샷 2022-02-06 오후 7.18.00.png](https://user-images.githubusercontent.com/39619488/152677229-1fdddcf8-ff0d-4d8b-b29e-4d88560b6f95.png
)

`<dependencyManagement>` 에 추가한 라이브러리에 대한 버전이 정의되어 있으면

라이브러리 추가 시 따로 버전을 기입하지 않아도 자동으로 추가되며 그로인해 버전 호환성에 대한 비용이 줄어드는 장점을 가지고 있다.

허나 스프링부트가 버전을 관리해주는 라이브러리가 아닌 경우에는 버전을 명시해주어여 하는데

이는 배포되는 환경마다 버전이 다른 경우가 생길 수가 있기 때문이다.

---

# 자동 설정 이해

`@SpringBootApplication` 에는 다음과 같은 어노테이션이 포함되어 있다

`@SpringBootConfiguration` `@ComponentScan` `@EnableAutoConfiguration`

스프링부트는 Bean을 두번 등록하는데

첫번째로는`@ComponentScan` 을 통해 패키지 내의 `@Configuration` 설정된 클래스들을 읽어서 Bean으로 등록하고, 두번째로는 `@EnableAutoConfiguration` 을 통해 추가적인 Bean을 등록한다.

![스크린샷 2022-02-07 오후 9.23.57.png](https://user-images.githubusercontent.com/39619488/152994153-b237ff43-fa4b-4669-8082-a86c8b95dbfc.png)

추가적인 Bean은 메타파일의 spring.factories에 등록되어 있다.

![스크린샷 2022-02-07 오후 9.26.00.png](https://user-images.githubusercontent.com/39619488/152994273-758c1bda-21f2-4d5d-905d-a42a0ed27713.png)

![스크린샷 2022-02-07 오후 9.26.36.png](https://user-images.githubusercontent.com/39619488/152994335-7e8bf930-ec79-4f1c-a7d3-a6734d69a918.png)

---

# 자동 설정 구현 Starter와 AutoConfigure

Xxx-Spring-Boot-Autoconfigure 모듈: 자동 설정

Xxx-Spring-Boot-Starter 모듈: 필요한 의존성 정의

그냥 하나로 만들고 싶을 때는? Xxx-Spring-Boot-Starter

구현방법

1. 의존성 추가

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-autoconfigure</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-autoconfigure-processor</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>

<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-dependencies</artifactId>
            <version>2.0.3.RELEASE</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

1. `@Configuration` 클래스 작성

   ![스크린샷 2022-02-07 오후 9.26.36.png](https://user-images.githubusercontent.com/39619488/152994514-2ebe47a5-b368-4ead-a373-d95516673b97.png)

2. src/main/resource/META-INF에 spring.factories 파일 만들기

   ![스크린샷 2022-02-07 오후 9.41.25.png](https://user-images.githubusercontent.com/39619488/152994540-f682d72e-dade-4d68-90ea-7213f0c80183.png)

3. spring.factories 안에 자동 설정 파일 추가

   ![스크린샷 2022-02-07 오후 9.41.47.png](https://user-images.githubusercontent.com/39619488/152994596-2ff2b8cd-db69-4f26-bd72-2c7fda17040d.png)

4. mvn install을 하면 자동설정 구현된 프로젝트가 jar파일 형태로 로컬에 생성되고, 추가하고자 하는 프로젝트에 해당 정보를 이용해서 의존성을 추가하면 Bean을 등록하지않아도 사용이 가능하다.

   ![스크린샷 2022-02-07 오후 9.45.18.png](https://user-images.githubusercontent.com/39619488/152994628-3bf948e9-3aec-4d0b-9df0-6641c21ffb22.png)

   ![스크린샷 2022-02-07 오후 9.48.21.png](https://user-images.githubusercontent.com/39619488/152994657-88b2370e-40e4-4ded-96bc-65fe838b08e4.png)

   ![스크린샷 2022-02-07 오후 9.52.52.png](https://user-images.githubusercontent.com/39619488/152994699-ee5249d1-8f00-44d4-8c9b-0044669d1686.png)


---

# 자동 설정 구현 @ConfigurationProperties

application.properties 통해서 구현

---

# 내장 웹서버 이해

스프링부트는 툴이다. 내장 서블릿 컨테이너를 쉽게 사용하게 해주는 툴이지 서버가 x

기본적으로 의존성에 톰캣이 존재하고, 이는 자동설정을 통해 존재한다.(spring.factories에 존재)

# 내장 웹서버 응용 : 컨테이너와 포트

[https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.webserver](https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.webserver)

자동설정된 톰캣이 아닌 다른 서블릿 컨테이너로 변경

```xml
    <exclusions>
        <!-- Exclude the Tomcat dependency -->
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>
    </exclusions>
</dependency>
<!-- Use Jetty instead -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jetty</artifactId>
</dependency>
```

웹서버 사용x application.properties

`spring.main.web-application-type=none`

포트 변경 [application.properties](http://application.properties) server.port = xxxx(0일시 랜덤)

포트를 애플리케이션에 사용

**3.5. Discover the HTTP Port at Runtime 참조**

이벤트리스너 역할하는 클래스 추가 포트리스너

# 내장 웹서버 응용 : HTTPS와 HTTP2

HTTPS를 사용하기 위해서는 키스토어를 먼저 생성해야 한다.

application.properties

```
server.ssl.key-store: keystore.p12
server.ssl.key-store-password: 123456
server.ssl.keyStoreType: PKCS12
server.ssl.keyAlias: spring
```

터미널

`keytool -genkey -alias spring -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.p12 -validity 4000`

톰캣이 사용하는 커넥터는 기본적으로 하나만 적용되기에 HTTP도 추가적으로 사용하기 위해서는

선행되어야 할 작업이 있다.

```java
// 커넥터 추가
@Bean
public ServletWebServerFactory serverFactory() {
    TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
    tomcat.addAdditionalTomcatConnectors(createStandardConnector());
    return tomcat;
}

private Connector createStandardConnector() {
    Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
    connector.setPort(8080);
    return connector;
}
```

HTTP2 활성화하기(SSL이 기본적으로 적용이 되어있어야함)

undertow

application.properties

`server.http2.enabled=true`