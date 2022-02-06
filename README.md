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