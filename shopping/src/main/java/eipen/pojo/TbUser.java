package eipen.pojo;


import com.alibaba.druid.util.StringUtils;

public class TbUser implements IsEmpty{

  private Long id;
  private String username;
  private String password;
  private String nickname;
  private String email;
  private String gender;
  private Long flag;
  private Long role;
  private String code;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public Long getFlag() {
    return flag;
  }

  public void setFlag(Long flag) {
    this.flag = flag;
  }

  public Long getRole() {
    return role;
  }

  public void setRole(Long role) {
    this.role = role;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  @Override
  public String toString() {
    return "TbUser{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", nickname='" + nickname + '\'' +
            ", email='" + email + '\'' +
            ", gender='" + gender + '\'' +
            ", flag=" + flag +
            ", role=" + role +
            ", code='" + code + '\'' +
            '}';
  }

  @Override
  public boolean isNull() {
    return StringUtils.isEmpty(username)||StringUtils.isEmpty(nickname)||StringUtils.isEmpty(gender);
  }
}
