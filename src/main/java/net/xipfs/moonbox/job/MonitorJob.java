package net.xipfs.moonbox.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Async
@Slf4j
public class MonitorJob {
    @Scheduled(cron = "* 0/5 * * * ? ")
    public void monitorWallet() {

    }
}