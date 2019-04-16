<?php
	require_once 'koneksi.php';

	if($_SERVER['REQUEST_METHOD'] == 'POST') {
		$nama = $_POST['nama'];
        $nim = $_POST['nim'];
        $jurusan = $_POST['jurusan'];
        $prodi = $_POST['prodi'];
        $alamat = $_POST['alamat'];

		$query = "INSERT INTO tb_mahasiswa(nama, nim, jurusan, prodi, alamat) 
        VALUES('".$nama."','".$nim."','".$jurusan."','".$prodi."','".$alamat."');";

		$exeQuery = mysqli_query($konek, $query);

		echo ($exeQuery) ? 
		json_encode(array('kode' => 1,'pesan' => 'data berhasil diinsert')) : 
		json_encode(array('kode' => 2,'pesan' => 'data gagal diinsert'));
	} else {
		echo json_encode(array('kode' => 11, 'pesan' => 'request tidak valid'));
	}

?>