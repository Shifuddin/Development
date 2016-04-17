<?php
echo '<p>init disables</p>';
exit;
define("DB","/var/www/html/sms/db/review_data.db");
$db = new SQLite3(DB);

$db->exec('DROP TABLE IF EXISTS categories');
$db->exec('DROP TABLE IF EXISTS paper_category');
$db->exec('DROP TABLE IF EXISTS paper_rational');

// kick out all duplicates
$to_delete = Array(
		'scopus004','scopus123','scopus026','scopus051','scopus014','scopus057','scopus054',
		'scopus176','scopus149','scopus002','scopus094','scopus039','scopus027','scopus030',
		'scopus041','scopus163','scopus168','scopus137','scopus029','scopus182','scopus052',
		'scopus166','springer1392','acm027','ieee168','acm041'
		);
foreach($to_delete as $id){
$db->exec("DELETE FROM reviews WHERE id LIKE '".$id."'");
$db->exec("INSERT INTO reviews (id,reviewer,verdict) VALUES ('".$id."','Bot1','REJECT title')");
$db->exec("INSERT INTO reviews (id,reviewer,verdict) VALUES ('".$id."','Bot2','REJECT title')");

}


$db->exec('CREATE TABLE IF NOT EXISTS categories ( id INTEGER PRIMARY KEY AUTOINCREMENT,
												parent TEXT,
												name TEXT
												)');
$db->exec('CREATE TABLE IF NOT EXISTS paper_category ( paper_id TEXT,reviewer TEXT, category_id INTEGER )');
$db->exec('CREATE TABLE IF NOT EXISTS paper_rational ( paper_id TEXT, reviewer TEXT, rational TEXT )');


$db->exec("INSERT INTO categories (parent,name) VALUES ('Domain','Cloud')");
$db->exec("INSERT INTO categories (parent,name) VALUES ('Domain','E-Voting')");
$db->exec("INSERT INTO categories (parent,name) VALUES ('Domain','Health Care')");
$db->exec("INSERT INTO categories (parent,name) VALUES ('Domain','Web')");


$db->exec("INSERT INTO categories (parent,name) VALUES ('Technology','Logging')");
$db->exec("INSERT INTO categories (parent,name) VALUES ('Technology','DRM')");
$db->exec("INSERT INTO categories (parent,name) VALUES ('Technology','Web Services')");


$db->exec("INSERT INTO categories (parent,name) VALUES ('Protocol','P2P')");
$db->exec("INSERT INTO categories (parent,name) VALUES ('Protocol','Network Protocol')");
$db->exec("INSERT INTO categories (parent,name) VALUES ('Protocol','Cryptographic Protocol')");

$db->exec("INSERT INTO categories (parent,name) VALUES ('Properties','Traceability')");
$db->exec("INSERT INTO categories (parent,name) VALUES ('Properties','Provenance')");
$db->exec("INSERT INTO categories (parent,name) VALUES ('Properties','Forensic')");
$db->exec("INSERT INTO categories (parent,name) VALUES ('Properties','Privacy')");
$db->exec("INSERT INTO categories (parent,name) VALUES ('Properties','Security')");
$db->exec("INSERT INTO categories (parent,name) VALUES ('Properties','Safety')");
$db->exec("INSERT INTO categories (parent,name) VALUES ('Properties','Trust')");
$db->exec("INSERT INTO categories (parent,name) VALUES ('Properties','Legal Compliance')");
$db->exec("INSERT INTO categories (parent,name) VALUES ('Properties','Standards Related/Compliance')");

$db->exec("INSERT INTO categories (parent,name) VALUES ('Mechanism','Access Control')");
$db->exec("INSERT INTO categories (parent,name) VALUES ('Mechanism','Anonymity')");

$db->exec("INSERT INTO categories (parent,name) VALUES ('Sanity Checks','No Implemntation')");
$db->exec("INSERT INTO categories (parent,name) VALUES ('Sanity Checks','Not in English')");
$db->exec("INSERT INTO categories (parent,name) VALUES ('Sanity Checks','PDF not available')");
$db->exec("INSERT INTO categories (parent,name) VALUES ('Sanity Checks','Not about Accountability')");
$db->exec("INSERT INTO categories (parent,name) VALUES ('Sanity Checks','I am not sure, need help')");





echo "<p>Created Categories</p>";
$host  = $_SERVER['HTTP_HOST'];
$uri   = rtrim(dirname($_SERVER['PHP_SELF']), '/\\');
echo '<p><a href="http://'.$host.$uri.'/index.php">Start Reviewing!</a></p>';
