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

// create the new categories
$new_cats = Array();
for($i = 0; $i < 10;$i++){
	if(!empty($_POST['new_parent'][$i]) && !empty($_POST['new_parent'][$i])){
		$db->exec("INSERT INTO categories (parent,name) VALUES ('".$db->escapeString(trim($_POST['new_parent'][$i]))."','".$db->escapeString(trim($_POST['new_name'][$i]))."')");
		$new_cats[] = $db->lastInsertRowid();
	}
	
}
//echo '<pre>';
//print_r($_POST);
//print_r($new_cats);

foreach(array_merge($_POST['categories'],$new_cats) as $cat){
	$s = "INSERT INTO paper_category (paper_id,reviewer, category_id) VALUES 
		('".$db->escapeString(trim($_POST['id']))."','".$db->escapeString(trim($_SESSION['reviewer']))."',".(int)$cat.")";
	echo $s."\n";
	$db->exec($s);

}

$s = "INSERT INTO paper_rational (paper_id, reviewer, rational) VALUES
	('".$db->escapeString(trim($_POST['id']))."','".$db->escapeString(trim($_SESSION['reviewer']))."','".$db->escapeString(trim($_POST['rational']))."')";
//echo $s."\n";
$db->exec($s);

header('Location: /sms/keywording.php');
