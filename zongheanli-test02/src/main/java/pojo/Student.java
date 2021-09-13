package pojo;


import com.alibaba.druid.util.StringUtils;

public class Student implements CheckNull{

  private long stuId;
  private String stuName;
  private String stuGender;
  private String stuPhone;
  private java.util.Date bornDate;
  private String stuEmail;
  private String stuAddress;
  private long gradeId;


  public long getStuId() {
    return stuId;
  }

  public void setStuId(long stuId) {
    this.stuId = stuId;
  }


  public String getStuName() {
    return stuName;
  }

  public void setStuName(String stuName) {
    this.stuName = stuName;
  }


  public String getStuGender() {
    return stuGender;
  }

  public void setStuGender(String stuGender) {
    this.stuGender = stuGender;
  }


  public String getStuPhone() {
    return stuPhone;
  }

  public void setStuPhone(String stuPhone) {
    this.stuPhone = stuPhone;
  }


  public java.util.Date getBornDate() {
    return bornDate;
  }

  public void setBornDate(java.util.Date bornDate) {
    this.bornDate = bornDate;
  }


  public String getStuEmail() {
    return stuEmail;
  }

  public void setStuEmail(String stuEmail) {
    this.stuEmail = stuEmail;
  }


  public String getStuAddress() {
    return stuAddress;
  }

  public void setStuAddress(String stuAddress) {
    this.stuAddress = stuAddress;
  }


  public long getGradeId() {
    return gradeId;
  }

  public void setGradeId(long gradeId) {
    this.gradeId = gradeId;
  }

  @Override
  public boolean isNull() {
    return StringUtils.isEmpty(stuName)||StringUtils.isEmpty(stuGender)||StringUtils.isEmpty(stuPhone)||bornDate==null||StringUtils.isEmpty(stuEmail)||StringUtils.isEmpty(stuAddress)||gradeId<=0;
  }
}
