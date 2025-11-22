package com.example.marathon;

import com.example.marathon.dto.common.ImportResult;
import com.example.marathon.security.TokenService;
import com.example.marathon.service.AuthService;
import com.example.marathon.service.VolunteerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;

@SpringBootTest
class MarathonApplicationTests {

    @Autowired
    private VolunteerService volunteerService;

    @Test
    void contextLoads() {
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "volunteers.csv",
                "text/csv",
                """
                        姓名,城市名称,出生日期,性别
                        Alice,Suzhou,1990-05-06,Male
                        XXX,XXX,AAAA,Female
                        Bob,Nanjing,1985-12-01,Female
                        XXX,XXX,1985-12-01,Female
                        XXX,XXX,AAAA,AAA
                        """.getBytes(StandardCharsets.UTF_8)
        );
        ImportResult result = volunteerService.importCsv(file);
        System.out.println("Success Count: " + result.getSuccessCount());
        System.out.println("Failure Count: " + result.getFailCount());
        System.out.println("Errors: " + result.getFailedLines());
    }

}
