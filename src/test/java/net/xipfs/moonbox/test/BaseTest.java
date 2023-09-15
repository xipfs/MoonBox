package net.xipfs.moonbox.test;

import net.xipfs.moonbox.Application;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class BaseTest {
}
