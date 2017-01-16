<?php
error_reporting(0);
$con=mysqli_connect("localhost","root","","androidtest");
 
$userID = $_POST["userID"];
 
$sql = "SELECT * FROM `matches` WHERE `UserID`='$userID' ORDER BY `matchTime`;";
$result = mysqli_query($con, $sql);
 
$response = array();

while($row = mysqli_fetch_assoc($result)){
   
	    $response = array("MatchID"=>$row['MatchID'],"matchDate"=>$row['matchDate'],"matchTime"=>$row['matchTime'],"MatchType"=>$row['MatchType'],"lane"=>$row['lane']);

	
		echo json_encode(array("user_data"=>$response )) . "&";
	} 
?>