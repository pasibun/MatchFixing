<?php
error_reporting(0);
$con=mysqli_connect("localhost","root","","androidtest");
 
$MatchID = $_POST["MatchID"];

$sql = "DELETE FROM `matches`
		WHERE `MatchID`='$MatchID';";

$result = mysqli_query($con, $sql);
 
mysqli_close($con);
?>