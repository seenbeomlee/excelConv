package com.autocrypt.fms.server2u.repository.entity;

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
public class GroupDetail {

    @Id //PK 선언
    @Length(max = 32)
    @Column(nullable = false)
    private String id;

    @ManyToOne(targetEntity = GroupInfo.class)
    @JoinColumn(name="group_info_id")
    private GroupInfo groupInfo;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name="customer_id")
    private Customer customer;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name="member_id")
    private Customer memberId;

    @ManyToOne(targetEntity = Configure.class)
    @JoinColumn(name="state")
    private Configure state;

    @Column(nullable = false)
    private Date created;

    @Column(nullable = false)
    private Date updated;
}
