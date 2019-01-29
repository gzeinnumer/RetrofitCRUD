<?php
		
	require_once 'koneksi.php';
	
	$id = $_POST['id'];
	//$id = "3";
		
	$query = "DELETE FROM tb_biodata WHERE id='".$id."'";
	
	$exeQuery = mysqli_query($konek, $query);

		echo ($exeQuery) ? 
		json_encode(array('kode' => 1,'pesan' => 'data berhasil didelete')) : 
		json_encode(array('kode' => 2,'pesan' => 'data gagal didelete'));

?>