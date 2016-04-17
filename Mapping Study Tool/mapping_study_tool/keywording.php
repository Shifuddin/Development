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
			echo '<li><a href="keywording.php?reviewer='.$user.'">'.$user.'</a></li>';
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
<script type="text/javascript">

function check_form(){
	checkboxes = document.getElementsByName("categories[]");
	checked_one = false;
	for(i = 0; i < checkboxes.length; i++){
		if(checkboxes[i].checked){
			checked_one = true;
			break;
		}
	}
	if(!checked_one && document.getElementsByName("new_parent[]")[0].value == ""){
		alert("Pick at least one category");
		return false;
	}
//	alert(checkboxes.length);

	if(document.getElementById("rational").value == ""){
		alert("Please give a rational!");
		document.getElementById("rational").focus();
		return false;
	}
	return true;
}
</script>
</head>
<body>

<?php
echo '<table id="highscore">';
echo '<tr><th>Reviewer</th><th>Reviews</th></tr>';
$s = 'SELECT reviewer, count(*) AS revs FROM paper_rational GROUP BY reviewer ORDER BY revs DESC';
$result = $db->query($s);
$done = 0;
while($row = $result->fetchArray()){
    echo '<tr>';
    echo '<td>'.$row['reviewer'].'</td>';
    echo '<td>'.$row['revs'].'</td>';
    $done+= $row['revs'];
    echo '</tr>';
}
echo '<tr>';

$s = "SELECT count(*) as num FROM papers  
                WHERE 
                (
                (
                id in (SELECT id FROM reviews WHERE verdict LIKE 'ACCEPT' GROUP BY id HAVING COUNT(id) = 2) 
                AND  id not in (SELECT id FROM reviews WHERE verdict LIKE 'REJECT%')
                )
                OR id in (SELECT id FROM reviews WHERE verdict LIKE 'ACCEPT' GROUP BY id HAVING COUNT(id) = 3)
                )
";
$r = $db->query($s);
$total = $r->fetchArray()['num'];
echo '<td>TODO:</td><td>'.($total - $done).'</td></tr>';

echo '</table>';



echo '<p>Hello, '.$_SESSION['reviewer'].'!</p>';


$s = "SELECT id,authors,title,abstract,url FROM papers  
                WHERE 
				(
				(
				id in (SELECT id FROM reviews WHERE verdict LIKE 'ACCEPT' GROUP BY id HAVING COUNT(id) = 2) 
				AND  id not in (SELECT id FROM reviews WHERE verdict LIKE 'REJECT%')
				)
				OR id in (SELECT id FROM reviews WHERE verdict LIKE 'ACCEPT' GROUP BY id HAVING COUNT(id) = 3)
				)
				AND id not in (SELECT paper_id FROM paper_category)
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

echo "<form action=\"post_categorisation.php\" method=\"POST\" onsubmit=\"return check_form()\">\n";
echo '<input type="hidden" name="id" value="'.$paper['id'].'" />'."\n";
$s = 'SELECT COUNT(*) as num FROM categories ';
$num_categories = $db->query($s)->fetchArray()['num'];
//echo $num_categories;
$s = 'SELECT id,parent,name FROM categories ORDER BY parent, name';
$result = $db->query($s);
$first = true;
$parent = '';
while($cat = $result->fetchArray()){
	if($cat['parent'] != $parent){
		if($first){
			$first = false;
		}else{
			echo '</table>';
		}
		echo '<table class="category"><tr><th colspan="2">'.$cat['parent'].'</th></tr>';
		$parent = $cat['parent'];	
	}
	echo '<tr><td><input type="checkbox" name="categories[]" value="'.$cat['id'].'" /></td><td>'.$cat['name']. '</td></tr>';	
}
echo '</table>';

echo '<p style="clear:both;">Add your own categories: </p>';
echo '<table>';
echo '<tr>';
echo '<th>Parent</th><th>Name</th>';
echo '</tr>';
for($i = 0; $i < 10;$i++){
	echo '<tr>';
	echo '<td><input type="text" name="new_parent[]" /></td><td><input type="text" name="new_name[]"/></td>';
	echo '</tr>';
}
echo '</table>';
echo '<p style="clear:both;">Please give a short rational: </p>';
echo '<p style="clear:both;"><textarea name="rational" id="rational" rows="8" cols="60"></textarea> </p>';
echo '<p style="clear:both;"><input type="submit" value="Submit!"/></p>';
echo '</form>';


?>

</body>
</html>
