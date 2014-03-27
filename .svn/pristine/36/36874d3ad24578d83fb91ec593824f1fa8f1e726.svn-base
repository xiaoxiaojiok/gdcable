<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<script type="text/javascript">
	window.onload = function(){
		var userlist = document.getElementById("userlist").value;
		var roll = document.getElementById("roll").value;
		roll = roll.replace(new RegExp("`", 'g'), "\"");
		window.name = userlist + "@#$%^&*" +roll;
	}
</script>
</head>
<body>
<input type="hidden" name="userlist" id="userlist" value='${userlist }'/>
<input type="hidden" name="roll" id="roll" value="${roll }"/>
</body>
</html>
