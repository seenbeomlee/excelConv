package com.autocrypt.fms.server2u.repository.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity // Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ApiModel(value="관리자 정보")
public class Admin implements UserDetails {
  public Admin(String id) {
    this.id = id;
  }
  
  @Id //PK 선언
  @Length(max = 32)
  @Column(nullable = false)
  @ApiModelProperty(value="관리자ID")
  private String id;

  @ManyToOne(targetEntity = CenterInfo.class)
  @JoinColumn(name="center_id")
  @ApiModelProperty(value="센터ID")
  private CenterInfo centerId;

  @Length(max = 32)
  @Column(nullable = false)
  @ApiModelProperty(value="로그인ID")
  private String loginId;

  @Length(max = 64)
  @Column(nullable = false)
  @ApiModelProperty(value="비밀번호")
  private String password;

  @Length(max = 32)
  @Column(nullable = false)
  @ApiModelProperty(value="이름")
  private String name;

  @Length(max = 32)
  @Column(nullable = false)
  @ApiModelProperty(value="연락처")
  private String extension;

  @Length(max = 8)
  @Column(nullable = false)
  @ApiModelProperty(value="권한")
  private String role;

  @Column(nullable = false)
  @ApiModelProperty(value="생성일시")
  private Date created;

  @Column(nullable = false)
  @ApiModelProperty(value="수정일시")
  private Date updated;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    authorities.add(new SimpleGrantedAuthority("USER"));
    if (role != null && role.equals("admin")) {
      authorities.add(new SimpleGrantedAuthority("ADMIN"));
    }
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return name;
  }

  @Override
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return false;
  }

}
