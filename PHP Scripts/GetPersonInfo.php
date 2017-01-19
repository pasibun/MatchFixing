<?php
error_reporting(0);
$con=mysqli_connect("localhost","root","","androidtest");
 
$sql = "SELECT firstName, lastName FROM `profile`;";

$result = mysqli_query($con, $sql);
 
$response = array();

while($row = mysqli_fetch_assoc($result)){	
    $response = array("firstName"=>$row['firstName'], "lastName"=>$row['lastName']);
	echo json_encode(array("user_data"=>$response )) . "&";
}
?>