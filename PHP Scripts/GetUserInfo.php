<?php
error_reporting(0);
$con=mysqli_connect("localhost","root","","androidtest");
 
$userID = $_POST["UserID"];
 
$sql = "SELECT * FROM `profile` WHERE `id`='$userID'";
$result = mysqli_query($con, $sql);
 
$response = array();

while($row = mysqli_fetch_assoc($result)){
   
	     $response = array(
	     	"UserName"=>$row['username'],
	     	"name"=>$row['firstName'],
	     	"gender"=>$row['gender'],
	     	"email"=>$row['email'],
	     	"adress"=>$row['adress'],
	     	"city"=>$row['city'],
	     	"dateOfBirth"=>$row['dateOfBirth'],
	     	"mobilePhone"=>$row['mobilePhone'],
	     	"phone"=>$row['phone'],
	     	"scoreSingle"=>$row['scoreSingle'],
	     	"scoreDouble"=>$row['scoreDouble']
	     	);

	
	echo json_encode(array("user_data"=>$response ));
} 
?>