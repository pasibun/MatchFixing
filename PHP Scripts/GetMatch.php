<?php
error_reporting(0);
$con=mysqli_connect("localhost","root","","androidtest");
 
$day = $_POST["day"];
$month = $_POST["month"];
$year = $_POST["year"];
 
$sql = "SELECT * FROM `matches` WHERE `matchDate`='".$year."-".$month."-".$day."';";
//$sql = "SELECT * FROM `matches` WHERE `matchDate`='2016-10-28';";
$result = mysqli_query($con, $sql);
 
$response = array();
 
while($row = mysqli_fetch_array($result)){
    $response = array("MatchID"=>$row[0],"matchDate"=>$row[1],"matchTime"=>$row[2],"MatchType"=>$row[3]);
}
 
echo json_encode(array("user_data"=>$response));
 
?>