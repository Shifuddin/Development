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
			echo '<li><a href="keyworded.php?reviewer='.$user.'">'.$user.'</a></li>';
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
// TODO simplify with joins
$s = "
SELECT id,title,url FROM papers
	WHERE id in (SELECT paper_id FROM paper_category)
	ORDER BY title
";
$result = $db->query($s);
echo '<table id="overview">';
while($paper = $result->fetchArray()){
	echo '<tr>';
	echo '<td><a href="paper.php?id='.$paper['id'].'">'.$paper['title'].'</a></td>';
	echo '<td>\''.$paper['id'].'\'</td>';
	echo '<td><a href="'.$paper['url'].'">[URL]</a></td>';
	echo '</tr>';
	$q = $db->query("SELECT reviewer,rational FROM paper_rational WHERE paper_id LIKE '".$paper['id']."'");
	$r = $q->fetchArray();
	$rational = $r['rational'];
	$reviewer = $r['reviewer'];
	echo '<tr><td colspan="2">'.$rational.'</td><td>'.$reviewer.'</td></tr>';
	$s = "SELECT c.parent,c.name FROM categories 
		c inner join (paper_category) pc 
		on (c.id = pc.category_id) 
		WHERE pc.paper_id LIKE '".$paper['id']."'
		ORDER BY c.parent, c.name
		";
	$q = $db->query($s);
	echo '<tr><td colspan="3">';
	while($cat = $q->fetchArray()){
		echo $cat['parent'].' - '.$cat['name'].',';
	}
	echo '</td></tr>';

}
echo '</table>';
