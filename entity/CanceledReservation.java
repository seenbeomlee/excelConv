package com.autocrypt.fms.server2u.repository.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;


import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CanceledReservation {

    @Id
    @Length(max = 32)
    @Column(nullable = false)
    private String id;

    @Length(max = 32)
    @Column(nullable = false)
    private String centerId;

    @Length(max = 32)
    @Column(nullable = false)
    private String reservationId;

    @ManyToOne(targetEntity = Driver.class)
    @JoinColumn(name="driver_id")
    private Driver driverId;

    @OneToOne(targetEntity = Vehicle.class)
    @JoinColumn(name="vehicle_id")
    private Vehicle vehicleId;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name="customer_id")
    private Customer customerId;

    @Length(max = 32)
    @Column
    private String customerName;

    @Length(max = 32)
    @Column
    private String customerNo;

    @Length(max = 32)
    @Column
    private String familyNo;

    @Length(max = 32)
    @Column
    private String familyRelation;

    @Length(max = 320)
    @Column(nullable = false)
    private String departure;

    @Length(max = 60)
    @Column(nullable = false)
    private String departurePoint;

    @Length(max = 320)
    @Column(nullable = false)
    private String destination;

    @Length(max = 60)
    @Column(nullable = false)
    private String destinationPoint;

    @Length(max = 320)
    @Column
    private String waypoint1;

    @Length(max = 60)
    @Column
    private String waypoint1Point;

    @Length(max = 320)
    @Column
    private String waypoint2;

    @Length(max = 60)
    @Column
    private String waypoint2Point;

    @Length(max = 320)
    @Column
    private String waypoint3;

    @Length(max = 60)
    @Column
    private String waypoint3Point;

    @Column(nullable = false)
    private Integer customerCnt;

    @Column(nullable = false)
    private Integer vehicleCnt;

    @Length(max = 256)
    @Column(nullable = false)
    private String vehicleType;

    @Length(max = 256)
    @Column(nullable = false)
    private String customerUsage;

    @Length(max = 32)
    @Column(nullable = false)
    private String luggage;

    @Column(nullable = false)
    private Integer serviceTime;

    @Length(max = 32)
    @Column(nullable = true)
    private String customerComment;

    @Length(max = 32)
    @Column(nullable = false)
    private String startTime;

    @Length(max = 32)
    @Column(nullable = false)
    private String arriveTime;

    @Column(nullable = false)
    private Integer driveTime;

    @Length(max = 32)
    @Column(nullable = false)
    private String endTime;

    @Column(nullable = false)
    private Integer fee;

    @ManyToOne(targetEntity = Configure.class)
    @JoinColumn(name="resrv_state")
    private Configure resrvState;

    @ManyToOne(targetEntity = Configure.class)
    @JoinColumn(name="suburban")
    private Configure suburban;

    @Length(max = 2)
    private String assignType;

    @Length(max = 8)
    private String payment;

    @Column(nullable = true)
    private Date payday;

    @Length(max = 32)
    private String depositName;

    @Column(nullable = true)
    private Integer cancelFee;

    @Column(nullable = false)
    private Date created;

    @Column(nullable = false)
    private Date updated;

    @Column
    private String departureComment;

    @Column
    private String destinationComment;

    @Column
    private String waypoint1Comment;

    @Column
    private String waypoint2Comment;

    @Column
    private String waypoint3Comment;

    @Length(max = 32)
    private String cancelComment;
}
