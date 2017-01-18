<?php
error_reporting(0);
$con=mysqli_connect("localhost","root","","androidtest");
 
 
$sql = "SELECT `id`, `username`, `firstName` FROM `profile` ORDER BY `firstName`;";
$result = mysqli_query($con, $sql);
 
$response = array();

while($row = mysqli_fetch_assoc($result)){
   
	    $response = array("userID"=>$row['id'],"userName"=>$row['username'],"firstName"=>$row['firstName']);

	
		echo json_encode(array("user_data"=>$response )) . "&";
	} 
?>