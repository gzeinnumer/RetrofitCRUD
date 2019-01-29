<?php
	require_once 'koneksi.php';

	if($_SERVER['REQUEST_METHOD'] == 'POST')
	{
		$nama = $_POST['nama'];
		$usia = $_POST['usia'];
		$domisili = $_POST['domisili'];

		$query = "INSERT INTO tb_biodata(nama, usia, domisili) VALUES('".$nama."','".$usia."','".$domisili."');";

		$exeQuery = mysqli_query($konek, $query);

		echo ($exeQuery) ? 
		json_encode(array('kode' => 1,'pesan' => 'data berhasil diinsert')) : 
		json_encode(array('kode' => 2,'pesan' => 'data gagal diinsert'));
	}
	else
	{
		echo json_encode(array('kode' => 11, 'pesan' => 'request tidak valid'));
	}

?>