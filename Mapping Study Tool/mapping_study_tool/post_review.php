<?php
require_once('shared.php');
session_start();
$db = new SQLite3(DB);

if($_SERVER['REQUEST_METHOD'] == 'POST'){
	$rq1 = isset($_POST['rq1']) ? 'YES' : 'NO';
	$rq2 = isset($_POST['rq1']) ? 'YES' : 'NO';
	$rq3 = isset($_POST['rq1']) ? 'YES' : 'NO';
	$rq4 = isset($_POST['rq1']) ? 'YES' : 'NO';

	$s = "SELECT count(id) as num FROM reviews WHERE verdict LIKE 'ACCEPT' and id LIKE '".$db->escapeString($_POST['id'])."'";
	$r = $db->query($s);
	$accept = $r->fetchArray()['num'];

	$s = "SELECT count(id) as num FROM reviews WHERE verdict LIKE 'REJECT%' and id LIKE '".$db->escapeString($_POST['id'])."'";
	$r = $db->query($s);
	$reject = $r->fetchArray()['num'];

	// "named" error cases
	if($accept + $reject >= 4){
		die("post_review.php: There was an error, at most 4 reviews per Paper are allowed!<a href=\"index.php\">Continue</a>");
	}
	if($accept == 2 && $reject == 0){
		die("post_review.php: There was an error, this paper has already two accepts!<a href=\"index.php\">Continue</a>");
	}
	if($accept == 0 && $reject == 2){
		die("post_review.php: There was an error, this paper has already two rejects!<a href=\"index.php\">Continue</a>");
	}

	//defensive coding, here is the list of valid values
	if(
		// not yet reviewed
		!($accept == 0 && $reject == 0) &&
		// first round
		!($accept == 1 && $reject == 0) &&
		!($accept == 0 && $reject == 1) &&
		// second round
		!($accept == 1 && $reject == 1) &&
		!($accept == 2 && $reject == 1) &&
		!($accept == 3 && $reject == 1) &&
		!($accept == 1 && $reject == 2) &&
		!($accept == 1 && $reject == 3) 
	){
		die("post_review.php: There was an unexpected error when storing your review for paper '{$_POST['id']}', accept was $accept and reject was $reject !<a href=\"index.php\">Continue</a>");
	}
	
	$query = "INSERT INTO reviews (id,reviewer, comments,verdict, rq1,rq2,rq3,rq4) 
									VALUES ('"
									.$db->escapeString($_POST['id'])."','"	
									.$db->escapeString($_POST['reviewer'])."','"	
									.$db->escapeString($_POST['comment'])."','"	
									.$db->escapeString($_POST['submit'])."',"
									."'".$rq1."',"	
									."'".$rq2."',"	
									."'".$rq3."',"	
									."'".$rq4."')";
//	echo $query;
	$db->exec($query);	
}
$_SESSION['reviewer'] = $_POST['reviewer'];
$host  = $_SERVER['HTTP_HOST'];
$uri   = rtrim(dirname($_SERVER['PHP_SELF']), '/\\');

header("Location: https://".$host.$uri."/index.php");

?>
