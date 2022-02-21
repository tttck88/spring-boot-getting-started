import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
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

