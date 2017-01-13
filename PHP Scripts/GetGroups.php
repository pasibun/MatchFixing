<?php
error_reporting(0);
$con=mysqli_connect("localhost","root","","androidtest");
 
$name = $_POST["username"];
 
$sql = "SELECT * FROM `groups` WHERE `username` = 'Member1'";

$result = mysqli_query($con, $sql);
 
$response = array();


while($row = mysqli_fetch_array($result)){
    $response = array("GroupName"=>$row['GroupName'], "Member1"=>$row['Member1'], "Member2"=>$row['Member2'], "Member3"=>$row['Member3'], "Member4"=>$row['Member4'], "Member5"=>$row['Member5'], "Member6"=>$row['Member6'], "Member7"=>$row['Member7'], "Member8"=>$row['Member8'], "Member9"=>$row['Member9'], "Member10"=>$row['Member10'], "Member11"=>$row['Member11'], "Member12"=>$row['Member12'], "Member13"=>$row['Member13'], "Member14"=>$row['Member14'], "Member15"=>$row['Member15']);
	echo json_encode(array("user_data"=>$response )) . "&";
}
?>