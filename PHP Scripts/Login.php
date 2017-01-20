<?php
error_reporting(0);
$con=mysqli_connect("localhost","root","","androidtest");
 
$name = $_POST["username"];
$password = $_POST["password"];
 
$sql = "SELECT * FROM `profile` WHERE `username`='".$name."' AND `password`='".$password."';";
 
$countmembers = mysqli_query($con, "SELECT COUNT(username) FROM 'profile'");
 
$result = mysqli_query($con, $sql);
 
$response = array();


while($row = mysqli_fetch_array($result)){
    $response = array("id"=>$row['id'], 
    	"username"=>$row['username'],
    	"password"=>$row['password'],
    	"gender"=>$row['gender'], 
    	"firstName"=>$row['firstName'],
		"lastName"=>$row['lastName'],
    	"email"=>$row['email'], 
    	"address"=>$row['address'], 
    	"city"=>$row['city'], 
    	"dateOfBirth"=>$row['dateOfBirth'], 
    	"mobilePhone"=>$row['mobilePhone'], 
    	"phone"=>$row['phone'], 
    	"scoreSingle"=>$row['scoreSingle'], 
    	"scoreDouble"=>$row['scoreDouble']);
}
 
echo json_encode(array("user_data"=>$response)); 
?>