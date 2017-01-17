<?php
error_reporting(0);
$con=mysqli_connect("localhost","root","","androidtest");

if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

$username =  $_POST["username"];
$name =  $_POST["name"];
$password = $_POST["password"];
$age =  $_POST["birthDate"];
$gender = $_POST["playerGender"];
$email = $_POST["email"];
$adress = $_POST["adress"];
$city = $_POST["city"];
$phone = $_POST["phone"];
$mobile = $_POST["mobile"];
$singleScore = $_POST["singleScore"];
$doubleScore = $_POST["doubleScore"];

$query = "INSERT INTO profile(username, password, gender, firstName, email, adress, city, dateOfBirth, mobilePhone, phone, scoreSingle, scoreDouble) 
				  VALUES('$username', '$password', '$gender', '$name', '$email', '$adress', '$city'
				  , '$age', '$mobile', '$phone', '$singleScore', '$doubleScore')";

$result = mysqli_query($con,$query);

mysqli_close($con);
?>