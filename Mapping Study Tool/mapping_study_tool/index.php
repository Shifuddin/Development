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
echo '<p>Hello, '.$_SESSION['reviewer'].'!</p>';

echo '<table id="highscore">';
echo '<tr><th>Reviewer</th><th>Reviews</th></tr>';
$s = 'SELECT reviewer, count(*) AS revs FROM reviews GROUP BY reviewer ORDER BY revs DESC';
$result = $db->query($s);
while($row = $result->fetchArray()){
	echo '<tr>';
	echo '<td>'.$row['reviewer'].'</td>';
	echo '<td>'.$row['revs'].'</td>';
	echo '</tr>';
}

echo '</table>';

// select a random paper  that,
// 1) Was not reviewed by the current user
// 2a) Has less than 2 reviews (First round of reviews)
// OR
// 2b) Has less than 4 reviews 
//     and one review is accept and one is reject (unclear paper) 
//     or has more than two reviews (but not four) so it is a paper that is in the 2nd review process
$s = "SELECT id,authors,title,abstract,url FROM papers 
WHERE 
id NOT IN (SELECT id FROM reviews WHERE reviewer LIKE '".$db->escapeString($_SESSION['reviewer'])."')
AND (
	id NOT IN (SELECT id  FROM reviews GROUP BY id HAVING COUNT(id) >= 2 ) 		
	OR
	(	
		id NOT IN (SELECT id  FROM reviews GROUP BY id HAVING COUNT(id) >= 4 )
		AND
		(
			id IN (
				SELECT DISTINCT r.id FROM reviews as r JOIN papers as p ON r.id = p.id
				WHERE r.id in (SELECT id FROM reviews WHERE verdict LIKE 'ACCEPT' GROUP BY id HAVING COUNT(id) = 1) 
				AND r.id in (SELECT id FROM reviews WHERE verdict LIKE 'REJECT%' GROUP BY id HAVING COUNT(id) = 1) 
			)	
			OR
			id IN (SELECT id  FROM reviews GROUP BY id HAVING COUNT(id) > 2 ) 
		)
	)
)
ORDER BY RANDOM() LIMIT 1
";
$result = $db->query($s);

if($paper = $result->fetchArray()){
	display_paper($paper);
}else{
	echo "<p>There are no more papers for you to review!</p><p><a href=\"https://www.youtube.com/watch?v=a-NgyryrB2U\">Enjoy the cut scene</a></p>";
	display_overview_table($db);
	echo '</body></html>';
	exit;
}

echo "<form action=\"post_review.php\" method=\"POST\">\n";
echo '<input type="hidden" name="id" value="'.$paper['id'].'" />'."\n";
echo '<table id="review_form">';
echo '<tr><td>Reviewer </td><td><input type="text" name="reviewer" value="';
if(!empty($_SESSION['reviewer'])){
	echo $_SESSION['reviewer'];
}
echo '"/></td></tr>';
echo "\n";
echo '<tr><td>Answers? </td><td>Research Question</td></tr>';
echo "\n";
echo '<tr><td><input type="checkbox" name="rq1"> </td><td>Paper discribes a tool, architecture or method. </td></tr>';
echo "\n";
echo '<tr><td><input type="checkbox" name="rq2"> </td><td>Paper focuses on privacy </td></tr>';
echo "\n";
echo '<tr><td><input type="checkbox" name="rq3"> </td><td>Paper focuses on security </td></tr>';
echo "\n";
echo '<tr><td><input type="checkbox" name="rq4"> </td><td>Paper focuses on safety </td></tr>';
echo "\n";
echo '<tr><td colspan="2">Comments: <br /> <textarea name="comment" rows="20" cols="90"> </textarea></td><td></td></tr>';
echo "\n";
echo '<tr>	<td><input type="submit" name="submit" value="REJECT abstract" /> </td>
			<td><input type="submit" name="submit" value="REJECT title"/> </td>
			<td><input type="submit" name="submit" value="ACCEPT"/> </td></tr>';
echo "\n";
echo "</table>\n";
echo '</form>';

display_overview_table($db);

?>

</body>
</html>
