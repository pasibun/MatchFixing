<?php
error_reporting(0);
$con=mysqli_connect("localhost","root","","androidtest");
 
$hourBefore = $_POST["hourBefore"];
$hourAfter = $_POST["hourAfter"];
$matchDate = $_POST["matchDate"];
 
$sql = "SELECT `lane` FROM `matches` WHERE `matchTime` BETWEEN '$hourBefore' AND '$hourAfter' AND `matchDate` = '$matchDate';";
$result = mysqli_query($con, $sql);
 
$response = array();

while($row = mysqli_fetch_assoc($result)){
   
	    $response = array("lane"=>$row['lane']);	
		echo json_encode(array("user_data"=>$response )) . "&";
	} 
?>