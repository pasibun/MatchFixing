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
    $response = array("id"=>$row[0], 
    	"username"=>$row[1],
    	"password"=>$row[2],
    	"gender"=>$row[3], 
    	"firstName"=>$row[4], 
    	"email"=>$row[5], 
    	"address"=>$row[6], 
    	"city"=>$row[7], 
    	"dateOfBirth"=>$row[8], 
    	"mobilePhone"=>$row[9], 
    	"phone"=>$row[10], 
    	"scoreSingle"=>$row[11], 
    	"scoreDouble"=>$row[12]);
}
 
echo json_encode(array("user_data"=>$response)); 
?>