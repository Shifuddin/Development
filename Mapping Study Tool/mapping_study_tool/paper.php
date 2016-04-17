<?php
session_start();
require('shared.php');
$db = new SQLite3(DB);

?>
<!DOCTYPE html> 
<html>
<head>
<title>Paper Review Tool -- Display a paper</title>
<link rel="stylesheet" href="main.css" />
</head>
<body>
<?php
$host  = $_SERVER['HTTP_HOST'];
$uri   = rtrim(dirname($_SERVER['PHP_SELF']), '/\\');
echo '<p><a href="index.php">Back to the review form</a></p>'; 
$query= 'SELECT id,authors,title,abstract,url FROM papers'.
		' WHERE id LIKE \''.$db->escapeString($_GET['id']).'\' LIMIT 1';
$result = $db->query($query);
$paper = $result->fetchArray();
display_paper($paper);


$results = $db->query('SELECT id,reviewer, verdict,comments,rq1,rq2,rq3,rq4 FROM reviews WHERE id LIKE \''.$paper['id'].'\'');

while($row = $results->fetchArray()){
	echo '<table border="1">';
	echo '<tr><td>Reviewer </td><td>';
	echo $row['reviewer'];
	echo '</td></tr>';
	echo "\n";
	echo '<tr><td>Answers? </td><td>Research Question</td></tr>';
	echo "\n";
	echo '<tr><td>';
	echo $row['rq1'];
	echo '</td><td>Paper discribes a tool, architecture or method.  </td></tr>';
	echo "\n";
	echo '<tr><td>';
	echo $row['rq2'];
	echo '</td><td>Paper focuses on privacy </td></tr>';
	echo "\n";
	echo '<tr><td>';
	echo $row['rq3'];
	echo '</td><td>Paper focuses on security</td></tr>';
	echo "\n";
	echo '<tr><td>';
	echo $row['rq4'];
	echo '</td><td>Paper focuses on safety  </td></tr>';
	echo "\n";
	echo '<tr><td colspan="2">Comments: <br /> <textarea name="comment" rows="20" cols="90">'.$row["comments"].' </textarea></td><td></td></tr>';
	echo "\n";
	$color = "green";
	// reject
	if(substr($row['verdict'],0,1) == "R"){
		$color = "red";
	}
	echo "<tr><td colspan=\"2\" bgcolor=\"$color\">".$row['verdict'].'</td></tr>';
	echo "\n";
	echo "</table>\n";
	echo '</form>';
}
echo '<p><a href="index.php">Back to the review form</a></p>'; 
display_overview_table($db);
?>

</body>
</html>

