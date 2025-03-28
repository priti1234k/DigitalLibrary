package com.digital.library.dlbms.controller;

import com.digital.library.dlbms.DlbmsApplication;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.digital.library.dlbms.constants.URIPaths.*;

@RestController
@RequestMapping(API_V1 + SYSTEM)
public class SystemController {



    @GetMapping(EXIT)
    public ResponseEntity<Void> exitSystem(){
        Thread exitTriggerThread = new Thread(
                () -> {
                    ConfigurableApplicationContext ctx = new SpringApplicationBuilder(DlbmsApplication.class)
                            .web(WebApplicationType.NONE).run();
                    int exitCode = SpringApplication.exit(ctx, new ExitCodeGenerator() {
                        @Override
                        public int getExitCode() {
                            return 0;
                        }
                    });
                    System.exit(exitCode);
                }
        );
        exitTriggerThread.start();
        return ResponseEntity.ok().build();
    }
}
