package com.autocrypt.fms.server2u.repository.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

@Entity // Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TermsType {
    @Id //PK 선언
    @Length(max = 32)
    @Column(nullable = false)
    private String id;

    @Length(max = 1024)
    @Column(nullable = false)
    private String termsType;

    // type에 해당하는 글이 있는지 여부
    @Length(max = 2)
    @Column(nullable = false)
    private String flag;

    @Length(max = 32)
    @Column(nullable = false)
    private String centerId;

    @ManyToOne(targetEntity = Configure.class)
    @JoinColumn(name="app_type")
    private Configure appType;

    @Column(nullable = false)
    private Date created;

    @Column(nullable = false)
    private Date updated;
}
