<%--
  Created by IntelliJ IDEA.
  User: Eipen
  Date: 2021/9/6
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>



</head>
<body>

<form action="/studentOperator?option=add" method="post" onsubmit="gradeId()">
  姓名：<input type="text" name="stuName"><br>
  性别：<input type="text" name="stuGender"><br>
  电话：<input type="text" name="stuPhone"><br>
  邮箱：<input type="text" name="stuEmail"><br>
  地址：<input type="text" name="stuAddress"><br>
  班级：<input type="text" name="gradeId" id="gradeId1"><br>
  生日：<input type="date" name="bornDate"><br>
  <input type="submit"><br>
</form>
<script type="application/javascript">
  var patt2 = /^[0,9]$/;
  var patt1 = new RegExp(patt2);
  let b = patt1.test(document.getElementById("gradeId1").innerText);
  alert(b);
  function gradeId(){

    // var myreg=/^[1][3,4,5,6.7,8,9][0-9]{9}$/;
    // var patt1=new RegExp(myreg);
    // document.write(patt1.test("13811748702"));
    if (!b) {
      alert("gradeId格式不对，只能数一位数字");
    }
  }
</script>
</body>
</html>
