<?php

	define("db_user", "a9645558_perico");
	define("db_password", "Ratikate2");
	define("db_name", "a9645558_juan");
	define("db_host", "mysql8.000webhost.com");
	$con = @mysqli_connect(db_host, db_user, db_password, db_name);
	
	if(!$con){
		die("Connection failed: " . mysqli_connect_error());
	}
?>
