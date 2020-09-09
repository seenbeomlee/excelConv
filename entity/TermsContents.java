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
public class TermsContents {
    @Id //PK 선언
    @Length(max = 32)
    @Column(nullable = false)
    private String id;

    @ManyToOne(targetEntity = TermsType.class)
    @JoinColumn(name="terms_type")
    private TermsType termsType;

    @JsonIgnoreProperties({"centerId"})
    @OneToOne(targetEntity = Admin.class)
    @JoinColumn(name="creator_id")
    private Admin creatorId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String contents;

    @Column(nullable = false)
    private Date created;

    @Column(nullable = false)
    private Date updated;

}
