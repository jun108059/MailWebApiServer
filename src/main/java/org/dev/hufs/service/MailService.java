package org.dev.hufs.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dev.hufs.entity.Mail;
import org.dev.hufs.repository.MailRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {

    private final MailRepository mailRepository;

    public List<Mail> saveMails() {
        String path = Objects.requireNonNull(MailService.class.getResource("")).getPath();
        log.info("## path : {}", path);

        File file = new File(path + "data.xlsx");
        List<Mail> mailList = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);

            XSSFSheet sheet = workbook.getSheetAt(0);
            int rows = sheet.getPhysicalNumberOfRows();
            for(int rowIndex = 1; rowIndex < rows ; rowIndex++) {
                Mail mail = new Mail();

                //행 읽기
                XSSFRow row = sheet.getRow(rowIndex);

                mail.setSender(String.valueOf(row.getCell(1)));
                mail.setTitle(String.valueOf(row.getCell(2)).substring(6));
                mail.setSendDateTime(String.valueOf(row.getCell(3)).substring(5));
                mail.setCategory(String.valueOf(row.getCell(4)));

                mailList.add(mail);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mailRepository.saveAll(mailList);
    }

    public Page<Mail> getMails(Pageable pageable) {
        return mailRepository.findAll(pageable);
    }
    public Page<Mail> getCategoryMails(String category, Pageable pageable) {
        return mailRepository.findByCategoryIs(category, pageable);
    }

}
