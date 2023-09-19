package net.xipfs.moonbox.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 配置信息
 *
 * @author hui.xie
 * @version 1.0
 * @since 2023/09/12/17:09
 */

@Configuration
@Data
public class MoonBoxConfig {
    @Value("${crypto.path}")
    private String dataPath;

    @Value("${secret.path}")
    private String secretPath;
}
