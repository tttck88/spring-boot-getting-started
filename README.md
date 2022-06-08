백기선 강사님의 [스프링 부트 개념과 활용 강의](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/dashboard)를 보고 공부한 내용입니다.

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

server.http2.enabled=true

---

# 독립적으로 실행 가능한 jar

---

# spring application

로깅

- 아무런 옵션없이 실행하면 애플리케이션의 로그레벨은 Info
- vmoption에 -debug를 추가한다거나 해서 로그레벨을 디버그로 설정 가능

배너

- banner.txt | gif | jpg | png
- classpath 또는 spring.banner.location
- ${spring-boot.version} 등의 변수를 사용할 수 있음.
- Banner 클래스 구현하고 SpringApplication.setBanner()로 설정 가능.
- 배너 끄는 방법
- SpringApplicationBuilder로 빌더 패턴 사용 가능

ApplicationEvent 등록

- bean으로 등록되어 있는 애들 중 해당하는 이벤트 리스너는 알아서 실행 됨
   - 애플리케이션 컨텍스트가 만들어진 다음에 발생하는 이벤트들은 bean 등록 어노테이션을 통해 실행할 수 있다.
   - 애플리케이션 컨텍스트가 만들어지기 전에 발생하는 이벤드들은 직접 등록을 해주어야 한다.

    ```java
    SpringApplication app = new SpringApplication(Application.class);
    // 아래 이벤트는 애플리케이션 컨텍스트가 실행되기 이전에 발생하기 떄문에 bean으로 등록하는 것이 아닌, 작접 등록해야함
    app.addListeners(new SampleListener());
    
    // 애플리케이션 컨텍스트가 실행되기 이전에 생성되기때문에 직접등록하지 않고 bean으로 등록하면 실행히 안된다.
    public class SampleListener implements ApplicationListener<ApplicationStartingEvent> {
    
        @Override
        public void onApplicationEvent(ApplicationStartingEvent applicationStartingEvent) {
            System.out.println("Application is starting");
        }
    }
    
    // 자동으로 실행이 됨
    @Component
    public class SampleListener2 implements ApplicationListener<ApplicationStartedEvent> {
    
        @Override
        public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
            System.out.println("Application is starting");
    
        }
    }
    ```


- 애플리케이션 아규먼트 사용하기
   - ApplicationArguments를 빈으로 등록해 주니까 가져다 쓰면 됨.
- 애플리케이션 실행한 뒤 뭔가 실행하고 싶을 때
   - ApplicationRunner (추천) 또는 CommandLineRunner
   - 순서 지정 가능 @Order


---

# 외부설정파일

애플리케이션에서 사용하는 여러가지 설정값들을 애플리케이션의 안팎애 정의해서 사용하는 기능

- properties - 스프링부트가 애플리케이션을 구동할때 자동으로 로딩하는 파일 ex) application.properties
- 커멘드라인아규먼트 ex) - java -jar target/springinit -0.0.1 -SNAPSHOT.jar —tttck.name = tttck
- YAML
- 환경변수

application.properties

```
#JAR 안에 있는 application.properties
tttck.name = tttck
tttck.age = ${random.int}
# 플레이스홀더 기능
tttck.fullName = ${tttck.name} Han
```

```java
@Component
public class SampleRunner implements ApplicationRunner {

    @Value("${tttck.name}")
    private String name;

    @Value("${tttck.age}")
    private String age;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("name");
    }
}

```

프로퍼티 우선 순위

1. 유저 홈 디렉토리에 있는 spring-boot-dev-tools.properties
2. 테스트에 있는 @TestPropertySource
3. @SpringBootTest 애노테이션의 properties 애트리뷰트
4. 커맨드 라인 아규먼트
5. SPRING_APPLICATION_JSON (환경 변수 또는 시스템 프로티) 에 들어있는 프로퍼티
6. ServletConfig 파라미터
7. ServletContext 파라미터
8. java:comp/env JNDI 애트리뷰트
9. System.getProperties() 자바 시스템 프로퍼티
10. OS 환경 변수
11. RandomValuePropertySource
12. JAR 밖에 있는 특정 프로파일용 application properties
13. JAR 안에 있는 특정 프로파일용 application properties
14. JAR 밖에 있는 application properties
15. JAR 안에 있는 application properties
16. @PropertySource
17. 기본 프로퍼티 (SpringApplication.setDefaultProperties)

application.properties 우선 순위 (높은게 낮은걸 덮어 씁니다.)

1. file:./config/
2. file:./
3. classpath:/config/
4. classpath:/

test에 따로 application.properties가 있을 경우 main과 같이 다르면 빌드시 에러 발생할 수 있음

왜냐하면 main에서 사용하려고 할 때 test에 해당 값이 없을 수 있기 때문에

(테스트코드 빌드할때 src/main에 있는 소스를 클래스패스에 넣고 그 다음 테스트코드를 빌드하면서 덮어쓰게 된다.. 그 때 application.properties가 오버라이딩)

이런 경우에 application.properties가 아닌 다른 이름으로 파일을 만들어서 사용하자

test.properties

```
# main에 application.properties와 다르기때문에 오버라이딩되지 않고 둘다 남아있는 상태로 빌트가 된다.
tttck.name=tttckTest3
```

```java
@TestPropertySource(locations = "classpath:/test.properties")
// 테스트에 있는 @TestPropertySource
//@TestPropertySource(properties = "tttck.name=tttckTest3")
// @SpringBootTest 어노테이션의 properties 애트리뷰트
// @SpringBootTest(properties = "tttck.name=tttckTest2")
public class ApplicationTest {

    @Autowired
    Environment environment;

    @Test
    public void contextLoads() {
assertThat(environment.getProperty("tttck.name"))
                .isEqualTo("tttckTest3");
    }
}
```

@ConfigurationProperties

외부 설정 많을 경우 같은 key로 시작하는 외부설정을 묶어서 하나의 빈으로 등록할 수 있음

```java
@Component
@ConfigurationProperties("tttck")
@Validated
public class TttckProperties {

    @NotEmpty
    private String name;
    private int age;
    private String fullName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
```

```java
@Component
public class SampleRunner implements ApplicationRunner {

//    @Value("${tttck.name}")
//    private String name;
//
//    @Value("${tttck.age}")
//    private String age;

    @Autowired
    TttckProperties tttckProperties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(tttckProperties.getName());
    }
}
```

융통성 있는 바인딩

- context-path (케밥)
- context_path (언드스코어)
- contextPath (캐멀)
- CONTEXTPATH

프로퍼티 타입 컨버전

- @DurationUnit

프로퍼티 값 검증

- @Validated
- JSR-303 (@NotNull, ...)

메타 정보 생성

- @Value
- SpEL 을 사용할 수 있지만...
- 위에 있는 기능들은 전부 사용 못합니다.
---

# HttpMessageConverters

- spring framework에서 제공하는 인터페이스, spring mvc에 일부분
- Http요청 본문으로 들어오는 것을 객체로 변경하거나, 객체를 Http응답 본문으로 변경할때 사용된다.
- *{“username”:”keesun”, “password”:”123”} <-> User*

@ResonesBody

@RequestBody

- 데이터가 요청으로 들어올때 본문에 그 데이터가 들어있고, 그것을 내가 객체로 받고싶을때 사용되어짐

HttpMessageConverters는 여러가지다. 그중에서 우리가 어떤 요청을 받았는지, 어떤 요청을 보내는지에 따라 사용되는 HttpMessageConverters가 달라진다.

ex) 요청이 Json이고 적혀있는 본문도 Json일때, Json메시지컨버터가 사용되어 Json메시지를 객체로 컨터팅해준다.

`public @ResponseBody User` 에서 User라는 객체 자체를 리턴하는 것이 아닌 Json메시지컨버터가 사용되어 Http문자로 Response해준다.

cf) 그냥 String일 경우 String메시지컨버터가 사용되어진다.
