<?php
	require_once 'koneksi.php';
	
	$id = $_POST['id'];
	$nama = $_POST['nama'];
	$usia = $_POST['usia'];
	$domisili = $_POST['domisili'];

	$query = "UPDATE tb_biodata SET nama = '".$nama."', usia = '".$usia."', domisili = '".$domisili."' WHERE id = '".$id."'";

		$exeQuery = mysqli_query($konek, $query);

		echo ($exeQuery) ? 
		json_encode(array('kode' => 1,'pesan' => 'data berhasil diupdate')) : 
		json_encode(array('kode' => 2,'pesan' => 'data gagal diupdate'));
?>

