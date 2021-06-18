package org.dev.hufs.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class MailSaveResponseDto {
    private int totalCount;
    private int page;
}
