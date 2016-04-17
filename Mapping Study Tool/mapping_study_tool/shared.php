<?php
define("DB","/var/www/html/sms/db/review_data.db");

function highlight($string){
	$search = array('Accountability','accountability','security','Security','safety','Safty','privacy','Privacy','tool','Tool','implementation','Implementation','application','Application');
	$replace = array('<b>Accountability</b>','<b>accountability</b>','<b>security</b>','<b>Security</b>','<b>safety</b>','<b>Safty</b>','<b>privacy</b>','<b>Privacy</b>','<b>tool</b>','<b>Tool</b>','<b>implementation</b>','<b>Implementation</b>','<b>application</b>','<b>Application</b>');
	return str_replace($search,$replace,$string);
}

function display_paper($paper){
	echo '<table id="paper">';
	echo "<tr>";
	echo "<td>";
	echo $paper['id'].':';
	echo '<a href="'.$paper['url'].'">';
	echo $paper['title'];
	echo "</a></td>";
	echo "<td>";
	echo $paper['authors'];
	echo "</td>";
	echo "</tr>\n";
	echo '<tr><td colspan="2">';
	echo highlight(htmlentities($paper['abstract']));
	echo "</td></tr>\n";
	echo "\n</table>\n\n";
}

function display_overview_table($db){
	$output =  "<table id=\"paper_list\">";
	$output.= '<tr><th colspan="15">List of all papers</th></tr>';
	$i = 1;
	$output.= "<tr>";
	$result = $db->query(	'SELECT id FROM papers
							ORDER BY id');
	$not_reviewed = 0;
	$one_accept = 0;
	$two_accept = 0;
	$one_reject = 0;
	$two_reject = 0;
	$three_one_accept = 0;
	$three_one_reject = 0;
	$unclear = 0;
	$two_two_unclear = 0;
	$limbo = 0;
	$three_two_accept = 0;
	$three_two_reject = 0;

	while($paper = $result->fetchArray()){
		$accept = 0;
		$reject = 0;
		$reviewed = $db->query('SELECT id,verdict FROM reviews WHERE id LIKE \''.$paper['id'].'\' ');
		while($row = $reviewed->fetchArray()){
			if($row['verdict'] == "ACCEPT"){
				$accept++;
			}else{
				$reject++;
			}
		}
	//	print_r($reviewed);
		$color = "white";
		if($accept + $reject > 5 || $accept + $reject < 0){
			die('shared.php:  $accept + $reject must be between 0 and 2');
		}
		if($accept == 0 && $reject == 0){
			$color = "grey";
			$not_reviewed++;
		}
		if($accept == 1 && $reject == 0){
			$color = "lightgreen";
			$one_accept++;
		}
		if($accept == 0 && $reject == 1){
			$color = "orange";
			$one_reject++;
		}
		if($accept == 2 && $reject == 0){
			$color = "green";
			$two_accept++;
		}
		if($accept == 0 && $reject == 2){
			$color = "red";
			$two_reject++;
		}
		if($accept == 1 && $reject == 1){
			$color = "royalblue";
			$unclear++;
		}
		if($accept == 3 && $reject == 1){
			$color = "darkolivegreen";
			$three_one_accept++;;
		}
		if($accept == 1 && $reject == 3){
			$color = "darkred";
			$three_one_reject++;
		}
		if($accept == 2 && $reject == 2){
			$color = "magenta";
			$two_two_unclear++;
		}
		if(($accept == 2 && $reject == 1) || ($accept == 1 && $reject == 2)){
			$color = "pink";
			$limbo++;
		}
		if($accept == 3 && $reject == 2){
			$color = "darkseagreen";
			$three_two_accept++;
		}
		if($accept == 2 && $reject == 3){
			$color = "firebrick";
			$three_two_reject++;
		}

		$output.= '<td bgcolor="'.$color.'"><a href="paper.php?id='.$paper['id'].'">'.$paper['id'].'</a></td>';
		

		if($i % 15 == 0 )
			 $output.= "</tr>\n<tr>";
		$i++;
	}
	$output.= "</tr>";

	$output.= "</table>";
	$s = 'SELECT COUNT(id) as number FROM papers';
	$r = $db->query($s);
	$total = $r->fetchArray();
	/*
	$s = "SELECT COUNT(id) as number FROM papers 
		WHERE id IN (SELECT id  FROM reviews WHERE verdict LIKE 'ACCEPT' GROUP BY id HAVING COUNT(id) >= 2) 
		OR id IN (SELECT id  FROM reviews WHERE verdict LIKE 'REJECT%' GROUP BY id HAVING COUNT(id) >= 2)
		OR id IN (SELECT id  FROM reviews GROUP BY id HAVING COUNT(id) >= 4)";
	$r = $db->query($s);
	$done = $r->fetchArray();
	*/

	$overview_table = '<table id="overall_overview">';		
	$overview_table.= '<tr><th colspan="2">Overview</th></tr>'."\n";
	$overview_table.= '<tr><td>Total number of papers</td><td>'.$total['number'].'</td></tr>'."\n";
	$overview_table.= '<tr bgcolor="grey"><td>Not yet reviewed</td><td>'.$not_reviewed.'</td></tr>'."\n";
	$overview_table.= '<tr bgcolor="lightgreen"><td>One accept</td><td>'.$one_accept.'</td></tr>'."\n";
	$overview_table.= '<tr bgcolor="orange"><td>One reject</td><td>'.$one_reject.'</td></tr>'."\n";
	$overview_table.= '<tr bgcolor="green"><td>Two accepts</td><td>'.$two_accept.'</td></tr>'."\n";
	$overview_table.= '<tr bgcolor="red"><td>Two reject</td><td>'.$two_reject.'</td></tr>'."\n";
	$overview_table.= '<tr bgcolor="royalblue"><td>Unclear</td><td>'.$unclear.'</td></tr>'."\n";
	$overview_table.= '<tr bgcolor="pink"><td>In limbo</td><td>'.$limbo.'</td></tr>'."\n";
	$overview_table.= '<tr bgcolor="darkolivegreen"><td>3-1 Accept</td><td>'.$three_one_accept.'</td></tr>'."\n";
	$overview_table.= '<tr bgcolor="darkred"><td>3-1 Reject</td><td>'.$three_one_reject.'</td></tr>'."\n";
	$overview_table.= '<tr bgcolor="magenta"><td>2-2 Unclear</td><td>'.$two_two_unclear.'</td></tr>'."\n";
	$overview_table.= '<tr bgcolor="firebrick"><td>3-2 Reject</td><td>'.$three_two_reject.'</td></tr>'."\n";
	$overview_table.= '<tr bgcolor="darkseagreen"><td>3-2 Accept</td><td>'.$three_two_accept.'</td></tr>'."\n";
	$overview_table.= '<tr><td>Completed Reviews</td><td>'.($two_accept + $two_reject + $three_one_accept + $three_one_reject  + $three_two_accept + $three_two_reject).'</td></tr>'."\n";
	$overview_table.= '</table>';		
	echo $overview_table;
	echo $output;
}
