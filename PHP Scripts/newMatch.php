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
$userID = 123;
$groupID = 123;
$matchDegree = $_POST["MatchDegree"];
$playerRankMin = $_POST["playerRankMin"];
$playerRankMax = $_POST["playerRankMax"];
$description = $_POST["Description"];

$query = "INSERT INTO matches(matchDate, matchTime, MatchType, GroupID, UserID, MatchDegree, playerRankMin, playerRankMax, Description) 
				  VALUES('$date', '$time', '$matchType', '$groupID', '$userID', '$matchDegree', '$playerRankMin', '$playerRankMax', '$description')";

$result = mysqli_query($con,$query);

mysqli_close($con);
?>