package com.company.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class ServerConfigure {

  @Id //PK 선언
  @Column(nullable = false)
  public LocalDateTime created = LocalDateTime.now();

  @Column(nullable = true)
  public String wcaUrl = "http://127.0.0.1:8000";

  @Column(nullable = true)
  public String wcaApiId;

  @Column(nullable = true)
  public String wcaApiAuthKey;

  @Column(nullable = true)
  public String wvaUrl = "http://127.0.0.1:18000";

  @Column(nullable = true)
  public String ocspUrl = wvaUrl+"/OCSPServer";

  @Column(nullable = true)
  public String cpsUrl;

  @Column(nullable = true)
  public String cpsApiId;

  @Column(nullable = true)
  public String cpsApiAuthKey;

  @Column(nullable = true)
  public String oemUrl;

  @Column(nullable = true)
  public String evServiceUrl;

  @Column(nullable = false)
  public LocalDateTime updated = LocalDateTime.now();
}
