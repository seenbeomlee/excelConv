package com.autocrypt.fms.server2u.repository.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

@Entity // Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ApiModel(value="증빙 서류")
public class Document {
    @Id //PK 선언
    @Length(max = 32)
    @Column(nullable = false)
    private String id;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name="customer_id")
    private Customer customer;

    @ManyToOne(targetEntity = Approvement.class)
    @JoinColumn(name="approvement_id")
    private Approvement approvementId;

    @ApiModelProperty(required=true, value="분실물 사진 URL")
    @Length(max = 64)
    private String photoUrl;

    @ManyToOne(targetEntity = Configure.class)
    @JoinColumn(name="type")
    private Configure type;

    @Column(nullable = false)
    private Date created;

    @Column(nullable = false)
    private Date updated;
}
