package com.autocrypt.fms.server2u.repository.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

@Entity // Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupInfo {

    @Id //PK 선언
    @Length(max = 32)
    @Column(nullable = false)
    private String id;

    @Length(max = 5)
    @Column(nullable = false)
    private String code;

    @Length(max = 32)
    @Column(nullable = false)
    private String centerId;

    @Length(max = 100)
    @Column(nullable = false)
    private String name;

    @ManyToOne(targetEntity = Configure.class)
    @JoinColumn(name="type")
    private Configure type;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name="owner_id")
    private Customer ownerId;

    @Column(nullable = false)
    private Integer memberCnt;

    @Column(nullable = false)
    private Date created;

    @Column(nullable = false)
    private Date updated;
}
