<?php
session_start();


if(isset($_GET["reviewer"])){
	$_SESSION['reviewer'] = $_GET['reviewer'];
}else{
	if(empty($_SESSION['reviewer'])){
		$_SESSION['reviewer'] = '';
		echo '<html><head><title>Paper Review Tool</title></head><body>';
		echo '<p>Not logged in, please pick a User:</p><ul style="list-style-type:none">';
		$users = Array('Florian', 'Kristian', 'Prachi', 'Severin');
		foreach($users as $user){
			echo '<li><a href="unclear.php?reviewer='.$user.'">'.$user.'</a></li>';
		}
		echo '</ul></body></html>';
		exit;
	}
}
require('shared.php');
$db = new SQLite3(DB);
?>
<!DOCTYPE html> 
<html>
<head>
<title>Paper Review Tool</title>
<link rel="stylesheet" href="main.css" />
</head>
<body>
<?php
$s = "
SELECT DISTINCT r.id,p.title,p.url,p.abstract FROM reviews as r JOIN papers as p ON r.id = p.id
                WHERE r.id in (SELECT id FROM reviews WHERE verdict LIKE 'ACCEPT' GROUP BY id HAVING COUNT(id) = 2) 
		AND r.id in (SELECT id FROM reviews WHERE verdict LIKE 'REJECT%' GROUP BY id HAVING COUNT(id) = 2) 
                ORDER BY p.title
";
$result = $db->query($s);
echo '<table id="overview">';
while($paper = $result->fetchArray()){
	echo '<tr>';
	echo '<td><a href="paper.php?id='.$paper['id'].'">'.$paper['title'].'</a></td>';
	echo '<td><a href="'.$paper['url'].'">[URL]</a></td>';
	echo '<td><a href="decide_unclear.php?id='.$paper['id'].'&verdict=accept" style="background-color:green;color:white;font-weight:bolder">Accept</a></td>';
	echo '<td><a href="decide_unclear.php?id='.$paper['id'].'&verdict=reject" style="background-color:red;color:white;font-weight:bolder;">Reject</a></td>';
	echo '</tr>';
	echo '<tr><td colspan="4">'.highlight($paper['abstract']).'</td></tr>';

}
echo '</table>';
