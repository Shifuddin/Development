<?php
require_once('shared.php');
session_start();
$db = new SQLite3(DB);
if(!empty($_GET['id']) && !empty($_GET['verdict'])){

	// check that is actually a undecided paper


	$s = "SELECT count(id) as num FROM reviews WHERE verdict LIKE 'ACCEPT' and id LIKE '".$db->escapeString($_GET['id'])."'";
	$r = $db->query($s);
	$accept = $r->fetchArray()['num'];

	$s = "SELECT count(id) as num FROM reviews WHERE verdict LIKE 'REJECT%' and id LIKE '".$db->escapeString($_GET['id'])."'";
	$r = $db->query($s);
	$reject = $r->fetchArray()['num'];

	if($accept != 2 || $reject != 2){
		die("Paper ".$_GET['id']." is not a 2-2 unclear paper! It is: accept $accept, reject $reject");
	}

	$verdict = '';
	if($_GET['verdict'] == 'accept'){
		$verdict = 'ACCEPT';
	}else{
		$verdict = 'REJECT abstract';
	}
	$query = "INSERT INTO reviews (id,reviewer, comments,verdict, rq1,rq2,rq3,rq4) 
									VALUES ('"
									.$db->escapeString($_GET['id'])."','group','','"	
									.$verdict."','','','','')";
//	echo $query;
	$db->exec($query);	
}
$host  = $_SERVER['HTTP_HOST'];
$uri   = rtrim(dirname($_SERVER['PHP_SELF']), '/\\');

header("Location: /sms/unclear.php");

?>
