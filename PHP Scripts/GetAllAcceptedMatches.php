<?php
error_reporting(0);
$con=mysqli_connect("localhost","root","","androidtest");
 
$MatchID = $_POST["MatchID"];

$sql = "SELECT * FROM `acceptedmatches`;";
$result = mysqli_query($con, $sql);
 
$response = array();

while($row = mysqli_fetch_assoc($result)){
   
	     $response = array("MatchID"=>$row['MatchID'],"MatchOwner"=>$row['MatchOwner'],"UserID2"=>$row['UserID2'],"UserID3"=>$row['UserID3'],"UserID4"=>$row['UserID4'], "SelectedMatchID"=>$MatchID);

	
	echo json_encode(array("user_data"=>$response )) . "&";
} 
?>