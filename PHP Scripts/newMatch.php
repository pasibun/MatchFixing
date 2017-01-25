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
$userID = $_POST["UserID"];
$groupID = $_POST["GroupID"];
$playerRankMin = $_POST["playerRankMin"];
$playerRankMax = $_POST["playerRankMax"];
$description = $_POST["Description"];
$lane = $_POST["lane"];

$query = "INSERT INTO matches(matchDate, matchTime, MatchType, GroupID, UserID, playerRankMin, playerRankMax, Description, lane) 
				  VALUES('$date', '$time', '$matchType', '$groupID', '$userID', '$playerRankMin', '$playerRankMax', '$description', '$lane')";

$result = mysqli_query($con,$query);

mysqli_close($con);
?>