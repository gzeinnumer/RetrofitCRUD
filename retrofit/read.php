<?php
	require_once 'koneksi.php';
	$query = "SELECT * FROM tb_mahasiswa ORDER BY nama";
	$result = mysqli_query($konek, $query);
	$array = array();
	
	while($row = mysqli_fetch_assoc($result))
	{
		$array[] = $row;
	}
	
	echo($result) ?
        json_encode(array('kode' =>1,'result'=>$array)) :
        json_encode(array('kode' =>2,'result' =>'Data tidak ditemukan'));
?>