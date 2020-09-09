package com.autocrypt.fms.server2u.repository.entity;

import io.swagger.annotations.ApiModelProperty;
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
public class StaredPlace {
    @Id //PK 선언
    @Length(max = 32)
    @Column(nullable = false)
    private String id;

    @ApiModelProperty(value="회원 ID")
    @Length(max = 32)
    @Column(nullable = false)
    private String userId;

    @ApiModelProperty(value="즐겨찾는 주소")
    @Length(max = 128)
    private String address;

    @ApiModelProperty(value="즐겨찾는 상세 주소")
    @Length(max = 128)
    private String addressDetails;

    @ApiModelProperty(value="별칭")
    @Length(max = 1024)
    private String nickName;

    @ApiModelProperty(value="즐겨찾는 주소 좌표 (ex. 126.925437,37.523500)")
    @Length(max = 60)
    @Column(nullable = false)
    private String latLng;

    @Column(nullable = false)
    private Date created;

    @Column(nullable = false)
    private Date updated;
}
