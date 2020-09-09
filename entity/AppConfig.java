package com.autocrypt.fms.server2u.repository.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AppConfig {

    @ApiModelProperty(value="앱 설정 ID")
    @Id
    @Length(max = 32)
    @Column(nullable = false)
    private String id;

    @ApiModelProperty(value="앱 설정 등록 일자")
    @Column(nullable = false)
    private Date created;

    @ApiModelProperty(value="앱 설정 갱신 일자")
    @Column(nullable = false)
    private Date updated;

    @ApiModelProperty(value="앱 종류")
    @Column
    private String appType;

    @ApiModelProperty(value="앱 최신 버전")
    @Length(max = 32)
    @Column
    private String latestVersion;

    @ApiModelProperty(value="앱 지원 최소 버전")
    @Length(max = 32)
    @Column
    private String minVersion;

    @ApiModelProperty(value="GPS 수집 주기")
    @Column
    private Integer gpsCycle;

    @ApiModelProperty(value="GPS 업데이트 주기")
    @Column
    private Integer updateCycle;
}
