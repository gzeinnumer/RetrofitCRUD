<?php
    require_once 'koneksi.php';

    $nama = $_POST['nama'];
    $nim = $_POST['nim'];
    $jurusan = $_POST['jurusan'];
    $prodi = $_POST['prodi'];
    $alamat = $_POST['alamat'];

    $query = "UPDATE tb_mahasiswa SET nama = '" . $nama . "', nim = '" . $nim . "', jurusan = '" . $jurusan . "', prodi = '" . $prodi . "', alamat = '" . $alamat . "' WHERE id = '" . $id . "'";

    $exeQuery = mysqli_query($konek, $query);

    echo ($exeQuery) ?
        json_encode(array('kode' => 1, 'pesan' => 'data berhasil diupdate')) :
        json_encode(array('kode' => 2, 'pesan' => 'data gagal diupdate'));
?>

