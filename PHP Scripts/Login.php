<?php
error_reporting(0);
$con=mysqli_connect("localhost","root","","androidtest");
 
$name = $_POST["name"];
$password = $_POST["password"];
 
$sql = "SELECT * FROM `leden` WHERE `name`='".$name."' AND `password`='".$password."';";
 
$result = mysqli_query($con, $sql);
 
$response = array();
 
while($row = mysqli_fetch_array($result)){
    $response = array("id"=>$row[0],"name"=>$row[1],"password"=>$row[2],"age"=>$row[3],"kaliber"=>$row[4]);
}
 
echo json_encode(array("user_data"=>$response));
 
?>