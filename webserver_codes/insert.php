<?php
date_default_timezone_set('Asia/Manila');
// header('Content-Type: application/json');
include('connect.php');
// $params = $this->getService()->getRequest()->getActionParams();
// echo json_encode($params);

// print_r($_POST);
$i = 0;
foreach($_POST as $key=>$value)
{
  $i++;
  // echo "$key=$value";
  // echo "\n";
}

$data = $mysqli->real_escape_string($_POST['data']);
// echo $data;
// echo "\n";
$data = substr($data, 1, -1);
//TODO: Process sndLevels
$dataArray = explode(';', $data);

if (count($dataArray) == 20) {
	$tablename = $dataArray[0];
	$date_time = $dataArray[1];

	for ($j = 2; $j < count($dataArray); $j++) {
		$resultQuery = $mysqli->query("INSERT INTO $tablename (
		date_time, 
		soundLevel
		) VALUES (
		'$date_time', 
		'$dataArray[$j]'
		)");
		$date_time++;
	}

	if($resultQuery) {
		echo "SUCCESS";
	} else {
		echo "FAIL";
	}
} else {
  echo "FAIL";
}

?>