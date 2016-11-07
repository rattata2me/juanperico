<?php
	require_once ("../mysql_connect");
	$score = $_POST("score");
	
	$scoredef = 0;
	
	if(is_int($score)){
		$scoredef = $score;
	}else if(str_is_int($score)){
		$scoredef = intval($score);
	}else{
		echo "wtf";
	}
	
	$lbscoret = "SELECT score FROM bscores LIMIT 0,1";
	$lbscore = 0;
	
	while($row = $libscoreret->fetch_assoc()){
		$lbscore = (int)$row["score"];
	}
	$need = false;
	if($lbscore < $scoredef){
		$need = true;
	}

	if($need){
		$sqlcodeu = "UPDATE bscores SET score=$scoredef";
		
		$sqlcodei = "INSERT INTO scores (score)
		VALUES ('$scoredef')";
	
		mysql_query($con, $sqlcode);
	}
	mysqli_close($con);
	//SELECT score FROM bscores LIMIT 0,1
	function str_is_int($input) {
		if ($input[0] == '-') {
			return ctype_digit(substr($input, 1));
		}
		return ctype_digit($input);
	}
?>
