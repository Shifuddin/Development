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
			echo '<li><a href="index.php?reviewer='.$user.'">'.$user.'</a></li>';
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
SELECT DISTINCT r.id,p.title,p.url FROM reviews as r JOIN papers as p ON r.id = p.id
                WHERE (r.id in (SELECT id FROM reviews WHERE verdict LIKE 'ACCEPT' GROUP BY id HAVING COUNT(id) = 2) 
		AND  r.id not in (SELECT id FROM reviews WHERE verdict LIKE 'REJECT%') )
		OR r.id in (SELECT id FROM reviews WHERE verdict LIKE 'ACCEPT' GROUP BY id HAVING COUNT(id) = 3)
                ORDER BY p.title
";
$result = $db->query($s);
echo '<table id="overview">';
while($paper = $result->fetchArray()){
	echo '<tr>';
	echo '<td><a href="paper.php?id='.$paper['id'].'">'.$paper['title'].'</a></td>';
	echo '<td>\''.$paper['id'].'\'</td>';
	echo '<td><a href="'.$paper['url'].'">[URL]</a></td>';
	echo '</tr>';

}
echo '</table>';
