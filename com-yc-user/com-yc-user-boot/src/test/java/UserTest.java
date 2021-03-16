import com.yc.user.AppMain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 12:37 2021/3/16
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppMain.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTest {
    @Test
    public void test(){
        System.out.println("asd");
    }
}
