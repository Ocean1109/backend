package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

/**
 * @author ocean
 */
@Data
@NoArgsConstructor
public class User {
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;
  private Date gmtCreate;
  private Date gmtModified;
  private String userAccount;
  private String userPassword;
  private String userRole;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(userAccount, user.userAccount);
  }
  @Override
  public int hashCode() {
    return Objects.hash(userAccount);
  }
}
