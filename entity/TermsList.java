package com.autocrypt.fms.server2u.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

@Entity // Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TermsList {
    @Id //PK 선언
    @Length(max = 32)
    @Column(nullable = false)
    private String id;

    @ManyToOne(targetEntity = TermsType.class)
    @JoinColumn(name="terms_type", unique = true)
    private TermsType typeId;

    @Length(max = 1024)
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Date created;

    @Column(nullable = false)
    private Date updated;

}
