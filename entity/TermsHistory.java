package com.autocrypt.fms.server2u.repository.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity // Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TermsHistory {
    @Id //PK 선언
    @Length(max = 32)
    @Column(nullable = false)
    private String id;

    @Length(max = 32)
    @Column(nullable = false)
    private String termsTypeId;

    @Length(max = 32)
    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private Boolean agreement;

    @Column(nullable = false)
    private Date created;

    @Column(nullable = false)
    private Date updated;
}
