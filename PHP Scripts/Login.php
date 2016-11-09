<?php
error_reporting(0);
$con=mysqli_connect("localhost","root","","androidtest");
 
$name = $_POST["name"];
$password = $_POST["password"];
 
$sql = "SELECT * FROM `profile` WHERE `name`='".$name."' AND `password`='".$password."';";
 
$countmembers = "SELECT COUNT(name) FROM 'profile'";
 
$result = mysqli_query($con, $sql, $countmembers);
 
$response = array();


while($row = mysqli_fetch_array($result)){
    $response = array("id"=>$row[0],"name"=>$row[1],"password"=>$row[2],"age"=>$row[3],"kaliber"=>$row[4], "amountmembers"=>$row[5] );
}
 
echo json_encode(array("user_data"=>$response)); 
?>