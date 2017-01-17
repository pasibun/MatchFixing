<?php
error_reporting(0);
$con=mysqli_connect("localhost","root","","androidtest");
 
$MatchID = $_POST["MatchID"];
$UserID3 = $_POST["UserID"];

$sql = "UPDATE `acceptedmatches` 
		SET UserID3 = $UserID3
		WHERE `MatchID`='$MatchID';";

$result = mysqli_query($con, $sql);
 
mysqli_close($con);
?>