<?php
error_reporting(0);
$con=mysqli_connect("localhost","root","","androidtest");
 
 
$sql = "SELECT `GroupID`, `GroupOwner` FROM `groups` ORDER BY `GroupID`;";
$result = mysqli_query($con, $sql);
 
$response = array();

while($row = mysqli_fetch_assoc($result)){
   
	    $response = array("groupID"=>$row['GroupID'],"groupOwner"=>$row['GroupOwner']);

	
		echo json_encode(array("user_data"=>$response )) . "&";
	} 
?>