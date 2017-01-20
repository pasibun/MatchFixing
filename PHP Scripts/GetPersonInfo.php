<?php
error_reporting(0);
$con=mysqli_connect("localhost","root","","androidtest");

$name = $_POST["username"];
 
$sql = "SELECT id, firstName, lastName, username FROM `profile` WHERE `username` <> '$name';";

$result = mysqli_query($con, $sql);
 
$response = array();

while($row = mysqli_fetch_assoc($result)){	
    $response = array("id"=>$row['id'], "firstName"=>$row['firstName'], "lastName"=>$row['lastName'], "userName"=>$row['username']);
	echo json_encode(array("user_data"=>$response )) . "&";
}
?>