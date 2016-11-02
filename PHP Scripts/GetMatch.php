<?php
error_reporting(0);
$con=mysqli_connect("localhost","root","","androidtest");
 
$day = $_POST["day"];
$month = $_POST["month"];
$year = $_POST["year"];
 
$sql = "SELECT * FROM `matches` WHERE `matchDate`='".$year."-".$month."-".$day."' ORDER BY `matchTime`;";
$result = mysqli_query($con, $sql);
 
$response = array();

while($row = mysqli_fetch_assoc($result)){
   
	     $response = array("MatchID"=>$row['MatchID'],"matchDate"=>$row['matchDate'],"matchTime"=>$row['matchTime'],"MatchType"=>$row['MatchType']);

	
	echo json_encode(array("user_data"=>$response )) . "&";
} 
?>