package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailScheduler {

    private final SimpleEmailService simpleEmailService;
    private final TaskRepository taskRepository;
    private final AdminConfig adminConfig;

    private static final String SUBJECT = "Tasks: Once a day email";

    @Scheduled(cron = "0 0 10 * * *") // 10 AM
    //@Scheduled(fixedDelay = 10000) // 10s
    public void sendInformationEmail() {
        long size = taskRepository.count();
        simpleEmailService.send(
                Mail.builder()
                        .mailTo(adminConfig.getAdminMail())
                        .subject(SUBJECT)
                        .message(size>1 ? "Currenty in databse you got: "+size+" tasks" : "Currenty in databse you got: "+size+" task")
                        .toCC(null)
                        .build()
        );
    }
}
