<?php
error_reporting(0);
$con=mysqli_connect("localhost","root","","androidtest");
 
$name = $_POST["username"];
$password = $_POST["password"];
 
$sql = "SELECT * FROM `profile` WHERE `username`='".$name."' AND `password`='".$password."';";
 
$countmembers = mysqli_query($con, "SELECT COUNT(ùsername) FROM 'profile'");
 
$result = mysqli_query($con, $sql);
 
$response = array();


while($row = mysqli_fetch_array($result)){
    $response = array("id"=>$row[0], "username"=>$row[1],"password"=>$row[2],"age"=>$row[3],"kaliber"=>$row[4], "gender"=>$row[5], "firstName"=>$row[6], "lastName"=>$row[7], "email"=>$row[8], "address"=>$row[9], "city"=>$row[10], "dateOfBirth"=>$row[11], "mobilePhone"=>$row[12], "phone"=>$row[13], "scoreSingle"=>$row[14], "scoreDouble"=>$row[15], "invited"=>$row[16], "countmembers"=>$countmembers);
}
 
echo json_encode(array("user_data"=>$response)); 
?>