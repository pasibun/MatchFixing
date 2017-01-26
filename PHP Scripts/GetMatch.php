<?php
error_reporting(0);
$con=mysqli_connect("localhost","root","","androidtest");
 
$day = $_POST["day"];
$month = $_POST["month"];
$year = $_POST["year"];
 
$sql = "SELECT * FROM `matches` WHERE `matchDate`='".$year."-".$month."-".$day."' AND `UserID` = 0 AND `GroupID` = 0 ORDER BY `matchTime`;";
$result = mysqli_query($con, $sql);
 
$response = array();

while($row = mysqli_fetch_assoc($result)){
   
	     $response = array("MatchID"=>$row['MatchID'],"matchDate"=>$row['matchDate'],"matchTime"=>$row['matchTime'],"MatchType"=>$row['MatchType'],"lane"=>$row['lane'], "Description"=>$row['Description']);

	
	echo json_encode(array("user_data"=>$response )) . "&";
} 
?>