<?php
error_reporting(0);
$con=mysqli_connect("localhost","root","","androidtest");

if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

$username =  $_POST["username"];
$password = $_POST["password"];
$age =  $_POST["age"];
$kaliber = $_POST["playerClass"];

<<<<<<< HEAD
$query = "INSERT INTO profile(name, password, age, kaliber) 
=======
$query = "INSERT INTO profile(username, password, age, kaliber) 
>>>>>>> login_getdata
				  VALUES('$username', '$password', '$age', '$kaliber')";

$result = mysqli_query($con,$query);
echo $username;
mysqli_close($con);
?>