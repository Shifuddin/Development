<?php
echo '<p>init disables</p>';
exit;
require_once('shared.php');
// remove the DB so we can recreate it cleanly
unlink(DB);

function parse_csv($file,$id_prefix,$author_field,$title_field,$abstract_field,$url_field,$year_field){
	$row = 1;
	$too_old = 0;
	$skip = 0;
	if (($handle = fopen($file, "r")) !== FALSE) {
		while (($data = fgetcsv($handle)) !== FALSE) {
			if((int)$data[$year_field] < 2005){
				$too_old++;
				continue;
			}
			if(!strstr($data[$abstract_field],'Accountability') && !strstr($data[$abstract_field],'accountability')&&
				!strstr($data[$title_field],'Accountability') && !strstr($data[$title_field],'accountability')){
				$skip++;
				continue;	
			}
			$paper = Array();
			$paper['id'] = $id_prefix.sprintf("%03d",$row);
			// author might sometimes be empty
			if(empty($data[$title_field]) || empty($data[$abstract_field]) || empty($data[$url_field])){
				echo "<p>Empty field  in paper $id_prefix$row!</p>";			
//				echo "<pre>";
//				print_r($data);
//				echo "</pre>";
//				continue;
			}
			$paper['title'] = $data[$title_field];
			$paper['author'] = $data[$author_field];
			$paper['abstract'] = $data[$abstract_field];
			$paper['url'] = $data[$url_field];
			if(isset($_GET['limit'])){	
				if($row > (int)$_GET['limit'])
					break;
			}
			$GLOBALS['papers'][] = $paper;
			$row++;

		}
		echo '<p>Skipped '.$too_old.' too old papers</p>';
		echo '<p>Skipped '.$skip.' papers, because the abstract does not contain the term &quot;accountability&quot;</p>';
		fclose($handle);
	}
}
function parse_springer($file){
	$row = 1;
	$skip = 0;
	$no_abstract = 0;
	if (($handle = fopen($file, "r")) !== FALSE) {
		while (($data = fgetcsv($handle)) !== FALSE) {
			$paper = Array();
			$paper['id'] = "springer".sprintf("%04d",$row);

			$paper['title'] = $data[0];
			$paper['author'] = $data[6];
			$paper['abstract'] = "";
			$paper['url'] = $data[8];
		//	echo '<p>'.$paper['url'] .'</p>';
		//	echo '<p>'.explode("/",$paper['url'],6)[5] .'</p>';
			$abstract_file = explode("/",$paper['url'],6)[5] ;
			$fp = @fopen("/var/www/html/search_string_results/accountability_tools/springer_abstracts_only/".$abstract_file,"r");
			if($fp){
				$paper['abstract'] = "";
				while (($buffer = fgets($fp, 4096)) !== false) {
					$paper['abstract'] .= strip_tags($buffer);
				}
				fclose($fp);
			}else{
				$paper['abstract'] = "Accountability No Abstract found! Please go to: ";
				$paper['abstract'] .= $paper['url'];
				$no_abstract++;
			}
			if(isset($_GET['limit'])){	
				if($row > (int)$_GET['limit'])
					break;
			}
			// 878 1336
			if(strstr($paper['abstract'],'Accountability') || strstr($paper['abstract'],'accountability')){
				$GLOBALS['papers'][] = $paper;
			}else{
				$skip++;
			}
			$row++;

		}
		echo '<p>Skipped '.$skip.' Springer papers, because the abstract does not contains &quot;Acc&quot; or &quot;acc&quot;</p>';
		echo '<p> '.$no_abstract.' Springer papers have no abstract</p>';
		fclose($handle);
	}
}

function print_csv($file,$limit = 3){
	if (($handle = fopen($file, "r")) !== FALSE) {
		$i = 0;
		while (($data = fgetcsv($handle)) !== FALSE) {
			echo "<pre>";
			print_r($data);
			echo "</pre>";
			if(++$i == $limit){
				break;
			}
		}
		fclose($handle);
	}
}

$db = new SQLite3(DB);
$db->exec('CREATE TABLE IF NOT EXISTS reviews (	id TEXT,
												reviewer TEXT,
												rq1 TEXT, 
												rq2 TEXT, 
												rq3 TEXT, 
												rq4 TEXT,
												comments TEXT,
												verdict TEXT
												)');
echo "<p>Created table reviews</p>";
$db->exec('CREATE TABLE IF NOT EXISTS papers (	id TEXT,
												authors TEXT,
												title TEXT,
												abstract TEXT,
												url TEXT
												)');
echo "<p>Created table papers</p>";

$papers = Array();
parse_csv(	'/var/www/html/raw_data/ieee1.csv',
			'ieee1',1,0,10,15,5);
echo "<p>Parsed IEEE 1</p>";
parse_csv(	'/var/www/html/raw_data/ieee2.csv',
			'ieee2',1,0,10,15,5);
echo "<p>Parsed IEEE 2</p>";
parse_csv(	'/var/www/html/raw_data/ieee3.csv',
			'ieee3',1,0,10,15,5);
echo "<p>Parsed IEEE 3</p>";
parse_csv(	'/var/www/html/raw_data/ieee4.csv',
			'ieee4',1,0,10,15,5);
echo "<p>Parsed IEEE 4</p>";

//function parse_csv($file,$id_prefix,$author_field,$title_field,$abstract_field,$url_field){
parse_csv(	'/var/www/html/raw_data/acm.csv',
			'acm',2,3,9,8,1);
echo "<p>Parsed ACM</p>";
parse_csv(	'/var/www/html/raw_data/fs1.csv',
			'fs1',0,1,15,12,2);
echo "<p>Parsed 1st degree forward snowballing</p>";
parse_csv(	'/var/www/html/raw_data/fs2.csv',
			'fs2',0,1,15,12,2);
echo "<p>Parsed 2st degree forward snowballing</p>";
/*
parse_csv(	'/var/www/html/search_string_results/accountability_tools/scopus.csv',
			'scopus',0,1,15,12);
echo "<p>Parsed scopus</p>";
parse_csv(	'/var/www/html/search_string_results/accountability_tools/acm_no_duplicates.csv',
			'acm',3,4,10,9);
echo "<p>Parsed ACM</p>";
//print_csv('/var/www/html/search_string_results/accountability_tools/springer_all.csv');
parse_springer('/var/www/html/search_string_results/accountability_tools/springer_all.csv');
echo "<p>Parsed Springer</p>";
*/
$i = 0;
foreach($papers as $paper){
	$query = "INSERT INTO papers (id,authors,title,abstract,url) 
									VALUES ('"
									.$db->escapeString($paper['id'])."','"	
									.$db->escapeString($paper['author'])."','"	
									.$db->escapeString($paper['title'])."','"	
									.$db->escapeString($paper['abstract'])."','"	
									.$db->escapeString($paper['url'])."')";
	$db->exec($query);
	$i++;
}
echo "<p>Added $i Papers to database</p>";
$host  = $_SERVER['HTTP_HOST'];
$uri   = rtrim(dirname($_SERVER['PHP_SELF']), '/\\');
echo '<p><a href="http://'.$host.$uri.'/index.php">Start Reviewing!</a></p>';
