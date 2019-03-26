<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
        rel="stylesheet">
<link href="main.css" rel="stylesheet">
<head>
<meta charset="UTF-8">
<title>Wiki Search</title>
</head>
<body>
	<div class="container">
		<img src="Wikipedia-logo.png" class="col-md-2 col-md-offset-5" height="10%" width="10%">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<form action="/topics" method="GET" name="searchForm" onsubmit="return validate()">
					<br/> 
					Wiki Search: <input type="text" placeholder="China, US (use , to seperate keywords)" name="key" style="width:200px">  
					<button class="btn" type="submit"> Search </button>
				</form>
			</div>
		</div>
	</div>
	<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
	<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<script> 
		function validate()                                    
		{ 
		    var keywords = document.forms["searchForm"]["key"];  
		   
		    if (keywords.value == "")                               
		    { 
		        window.alert("Please enter some searching keywords"); 
		        keywords.focus(); 
		        return false; 
		    }
		    if (keywords.value.match(/[.\/#!$%\^&\*;:{}=\-_`~()]/g))
			{
		    	window.alert("No special characters allowed except ,"); 
		        keywords.focus();
		        return false; 
			}
		    return true; 
		}
	</script> 
</body>
</html>