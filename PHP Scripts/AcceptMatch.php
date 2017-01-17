<?php
error_reporting(0);
$con=mysqli_connect("localhost","root","","androidtest");

if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

$MatchID =  $_POST["MatchID"];
$MatchOwner = $_POST["MatchOwner"];
$UserID2 =  $_POST["UserID2"];

$query = "INSERT INTO acceptedmatches(MatchID, MatchOwner, UserID2) 
				  VALUES('$MatchID', '$MatchOwner', '$UserID2')";

$result = mysqli_query($con,$query);
echo $username;
mysqli_close($con);
?>