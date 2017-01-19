<?php
error_reporting(0);
$con=mysqli_connect("localhost","root","","androidtest");
 
$groupname = $_POST["groupname"];
 
$sql = "DELETE FROM `groups` WHERE `GroupName` = '$groupname'";

$result = mysqli_query($con, $sql);
?>