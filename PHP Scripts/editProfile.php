  <?php
  error_reporting(0);
  $con=mysqli_connect("localhost","root","","androidtest");
  
  if (mysqli_connect_errno($con))
  {
     echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }
 
  $username =  $_POST["username"];
  $id = $_POST["id"];
  $name =  $_POST["name"];
  $lastname =  $_POST["lastName"];
  $password = $_POST["password"];
  $age =  $_POST["birthDate"];
  $gender = $_POST["playerGender"];
  $email = $_POST["email"];
  $address = $_POST["address"];
  $city = $_POST["city"];
  $phone = $_POST["phone"];
  $mobile = $_POST["mobile"];
  $singleScore = $_POST["singleScore"];
  $doubleScore = $_POST["doubleScore"];
  
  $query = "UPDATE `profile` SET username='$username',
  password='$password',
  gender='$gender',
  firstName='$name',
  lastName='$lastname',
  email='$email',
  address='$address',
  city='$city',
  dateOfBirth='$age',
  mobilePhone='$mobile',
  phone='$phone' ,
  scoreSingle='$singleScore',
  scoreDouble='$doubleScore' WHERE `id` = '$id';";
  
  
  $result = mysqli_query($con,$query);
  
  mysqli_close($con);
  ?>
