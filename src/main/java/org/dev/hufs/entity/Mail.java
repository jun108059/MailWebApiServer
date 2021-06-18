package org.dev.hufs.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="mail")
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Mail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mailIdx;

    /**
     * 보낸 사람
     */
    @Column(length = 200, nullable = false)
    private String sender;

    /**
     * 메일 제목
     */
    @Column
    private String title;

    /**
     * 발송 시간
     */
    private String sendDateTime;

    /**
     * 메일 유형
     */
    @Column
    private String category;
}