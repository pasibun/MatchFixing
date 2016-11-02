<?php
error_reporting(0);
$con=mysqli_connect("localhost","root","","androidtest");

if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

$date =  $_POST["matchDate"];
$time = $_POST["matchTime"];
$matchType =  $_POST["matchType"];

$query = "INSERT INTO matches(matchDate, matchTime, MatchType) 
				  VALUES('$date', '$time', '$matchType')";

$result = mysqli_query($con,$query);

mysqli_close($con);
?>