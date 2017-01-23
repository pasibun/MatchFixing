<?php
error_reporting(0);
$con=mysqli_connect("localhost","root","","androidtest");
 
$username = $_POST["username"];
$groupname = $_POST["groupname"];
$member2 = $_POST["member2"];
$member3 = $_POST["member3"];
$member4 = $_POST["member4"];
$member5 = $_POST["member5"];
$member6 = $_POST["member6"];
$member7 = $_POST["member7"];
$member8 = $_POST["member8"];
$member9 = $_POST["member9"];
$member10 = $_POST["member10"];
$member11 = $_POST["member11"];
$member12 = $_POST["member12"];
$member13 = $_POST["member13"];
$member14 = $_POST["member14"];
 
$sql = "INSERT INTO groups(GroupName, Member1, Member2, Member3, Member4, Member5, Member6, Member7, Member8, Member9, Member10, Member11, Member12, Member13, Member14, Member15) 
				  VALUES('$groupname', '$username', '$member2', '$member3', '$member4', '$member5', '$member6', '$member7', '$member8', '$member9', '$member10', '$member11', '$member12', '$member13', '$member14' )";

$result = mysqli_query($con, $sql);
 
$response = array();

while($row = mysqli_fetch_assoc($result)){  

	echo json_encode(array("user_data"=>$response ));
} 
?>